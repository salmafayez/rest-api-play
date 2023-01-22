package controllers

import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import enums.RoleService
import models.User
import play.api.i18n.Lang
import play.api.libs.json._
import play.api.mvc.{AnyContent, Request}
import responses.rest.Bad
import unused.json.Token

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

/**
 * The `Sign Up` controller.
 */
class SignUpController @Inject() (
  components: SilhouetteControllerComponents
)(implicit ex: ExecutionContext) extends SilhouetteController(components) {

  implicit val userFormat = Json.format[User]

  case class SignUp(email: String, password: String, firstName: String, lastName: String, role: Option[String])
  implicit val signUpFormat = Json.format[SignUp]

  /**
   * Handles sign up request
   *
   * @return The result to display.
   */
//  def signUp = UnsecuredAction.async { implicit request: Request[AnyContent] =>
//    implicit val lang: Lang = supportedLangs.availables.head
//    request.body.asJson.flatMap(_.asOpt[User]) match {
//      case Some(newUser) if newUser.password.isDefined =>
//        userService.retrieve(LoginInfo(CredentialsProvider.ID, newUser.email)).flatMap {
//          case Some(_) =>
//            Future.successful(Conflict(JsString(messagesApi("user.already.exist"))))
//          case None =>
//            val authInfo = passwordHasherRegistry.current.hash(newUser.password.get)
//            val user = newUser.copy(password = Some(authInfo.password))
//            userService.save(user).map(u => Ok(Json.toJson(u.copy(password = None))))
//        }
//      case _ => Future.successful(BadRequest(JsString(messagesApi("invalid.body"))))
//    }
//  }

  def signUp = Action.async(parse.json) { implicit request =>
    request.body.validate[SignUp].map { signUp =>
      val loginInfo = LoginInfo(CredentialsProvider.ID, signUp.email)
      userService.retrieve(loginInfo).flatMap {
        case None => /* user not already exists */
          val user = User(Some(0), signUp.firstName, signUp.lastName, signUp.email, null, RoleService.USER)
          val authInfo = passwordHasherRegistry.current.hash(signUp.password)
          for {
            userToSave <- userService.save(user.copy(password = Some(authInfo.password)))
            authInfo <- authInfoRepository.add(loginInfo, authInfo)
            authenticator <- silhouette.env.authenticatorService.create(loginInfo)
            token <- silhouette.env.authenticatorService.init(authenticator)
            result <- silhouette.env.authenticatorService.embed(token,
              Ok(Json.toJson(Token(token = token, expiresOn = authenticator.expirationDateTime)))
            )
          } yield {
            result
          }
        case Some(_) => /* user already exists! */
          Future(Conflict(Json.toJson(Bad(message = "user already exists"))))
      }
    }.recoverTotal {
      case error =>
        Future.successful(BadRequest(Json.toJson(Bad(message = JsError.toJson(error)))))
    }
  }
}
