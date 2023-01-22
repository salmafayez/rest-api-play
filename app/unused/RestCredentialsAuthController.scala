package security

//import com.mohiva.play.silhouette.api.{Env, Environment}
//import com.mohiva.play.silhouette.impl.authenticators.JWTAuthenticator
//import models.models.User
//
//import scala.concurrent.Future
//import play.api.libs.json._
//
//
//
//val jwtEnv = Environment[JWTEnv](...)
//
//class RestCredentialsAuthController extends Silhouette[User, JWTAuthenticator]
//  with HeaderEnvironmentModule {
//
//  // implicit format for transform json <==> Credential
//  implicit val restCredentialFormat = security.formatters.json.CredentialFormat.restFormat
//
//  /**
//   * Authenticates a user against the credentials provider.
//   *
//   * receive json like this:
//   * {
//   * "identifier": "...",
//   * "password": "..."
//   * }
//   *
//   * @return The result to display.
//   */
//  def authenticate = Action.async(parse.json[Credentials]) { implicit request =>
//    (env.providers.get(CredentialsProvider.ID) match {
//      case Some(p: CredentialsProvider) => p.authenticate(request.body)
//      case _ => Future.failed(new ConfigurationException(s"Cannot find credentials provider"))
//    }).flatMap { loginInfo =>
//      userService.retrieve(loginInfo).flatMap {
//        case Some(user) => env.authenticatorService.create(user.loginInfo).flatMap { authenticator =>
//          env.eventBus.publish(LoginEvent(user, request, request2lang))
//          env.authenticatorService.init(authenticator).flatMap { token =>
//            env.authenticatorService.embed(token, Future.successful {
//              Ok(Json.toJson(Token(token = token, expiresOn = authenticator.expirationDate)))
//            })
//          }
//        }
//        case None =>
//          Future.failed(new IdentityNotFoundException("Couldn't find user"))
//      }
//    }.recoverWith(exceptionHandler)
//  }
//
//}
//
//object RestCredentialsAuthController extends RestCredentialsAuthController