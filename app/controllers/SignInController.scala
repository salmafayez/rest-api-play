package controllers

import com.mohiva.play.silhouette.api.LoginEvent
import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.api.util.Credentials
import com.mohiva.play.silhouette.impl.exceptions.IdentityNotFoundException
import play.api.Configuration
import play.api.i18n.Lang
import play.api.libs.json.{JsString, Json}
import play.api.mvc.{AnyContent, Request}
import unused.json.Token

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

/**
 * The `Sign In` controller.
 */
class SignInController @Inject() (
  scc: SilhouetteControllerComponents,  configuration:Configuration
)(implicit ex: ExecutionContext) extends SilhouetteController(scc) {

  case class SignInModel(email: String, password: String)

  implicit val signInFormat = Json.format[SignInModel]

  /**
   * Handles sign in request
   *
   * @return JWT token in header if login is successful or Bad request if credentials are invalid
   */
//  def signIn = UnsecuredAction.async { implicit request: Request[AnyContent] =>
//    implicit val lang: Lang = supportedLangs.availables.head
//    request.body.asJson.flatMap(_.asOpt[SignInModel]) match {
//      case Some(signInModel) =>
//        val credentials = Credentials(signInModel.email, signInModel.password)
//        credentialsProvider.authenticate(credentials).flatMap { loginInfo =>
//          userService.retrieve(loginInfo).flatMap {
//            case Some(_) =>
//              for {
//                authenticator <- authenticatorService.create(loginInfo)
//                token <- authenticatorService.init(authenticator)
//                result <- authenticatorService.embed(token, Ok)
//              } yield {
//                logger.debug(s"User ${loginInfo.providerKey} signed success")
//                result
//              }
//            case None => Future.successful(BadRequest(JsString(messagesApi("could.not.find.user"))))
//          }
//        }.recover {
//          case _: ProviderException => BadRequest(JsString(messagesApi("invalid.credentials")))
//        }
//      case None => Future.successful(BadRequest(JsString(messagesApi("could.not.find.user"))))
//    }
//  }

  def signIn = Action.async(parse.json[SignInModel]) { implicit request =>
    val credentials =
      Credentials(request.body.email, request.body.password)
    credentialsProvider
      .authenticate(credentials)
      .flatMap { loginInfo =>
        userService.retrieve(loginInfo).flatMap {
          case Some(user) =>
            val config = configuration.underlying
            silhouette.env.authenticatorService
              .create(loginInfo)
              .map {
                case authenticator => authenticator
              }
              .flatMap { authenticator =>
                silhouette.env.eventBus.publish(LoginEvent(user, request))
                silhouette.env.authenticatorService
                  .init(authenticator)
                  .flatMap { token =>
                    silhouette.env.authenticatorService
                      .embed(
                        token,
                        Ok(
                          Json.toJson(
                            Token(
                              token,
                              expiresOn = authenticator.expirationDateTime
                            )
                          )
                        )
                      )
                  }
              }
          case None =>
            Future.failed(new IdentityNotFoundException("Couldn't find user"))
        }
      }
      .recover {
        case _: ProviderException =>
          Forbidden
      }
  }
}
