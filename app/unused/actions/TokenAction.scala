package unused.actions

import pdi.jwt.{Jwt, JwtAlgorithm}
import play.api.http.HeaderNames
import play.api.mvc._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success


class AuthAction @Inject() (parser: BodyParsers.Default)
                           (implicit ec: ExecutionContext)
  extends ActionBuilderImpl(parser) {

  override def invokeBlock[A](request: Request[A], block: Request[A] => Future[Result]) = {

    extractBearerToken(request) map{ token =>

      Jwt.decode(token, "scala-play-key", Seq(JwtAlgorithm.HS256)) match {
        case Success(value) =>
          println(value.content)
          block(request)
        case _ => println("not a valid token")
          Future.successful(Results.Forbidden)
      }
    } getOrElse Future.successful(Results.Unauthorized)
  }

  private def extractBearerToken[A](request: Request[A]): Option[String] =
    request.headers.get(HeaderNames.AUTHORIZATION) collect {
      case token => token.replace("Bearer ","")
    }
}
