//package controllers
//
//import com.mohiva.play.silhouette.api.exceptions.ProviderException
//import com.mohiva.play.silhouette.api.{LoginEvent, LoginInfo, Silhouette}
//import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
//import com.mohiva.play.silhouette.api.util.{Clock, Credentials, PasswordHasherRegistry}
//import com.mohiva.play.silhouette.impl.exceptions.IdentityNotFoundException
//import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
//import enums.RoleService
//import formatters.json.{CredentialFormat, Token}
//import models.security.SignUp
//import play.api.Configuration
//
//import javax.inject._
//import play.api.mvc._
//import play.api.libs.json.{JsError, Json}
//import responses.rest.Bad
//import service.UserService
//import unused.User
//
//import scala.concurrent.{ExecutionContext, Future}
//
//@Singleton
//class UserController @Inject()(val cc: ControllerComponents,
//                               components: ControllerComponents,
//                               userService: UserService,
//                               configuration: Configuration,
//                               silhouette: Silhouette[JWTEnv],
//                               clock: Clock,
//                               credentialsProvider: CredentialsProvider,
//                               authInfoRepository: AuthInfoRepository,
//                               passwordHasherRegistry: PasswordHasherRegistry)
//                              (implicit executionContext: ExecutionContext)
//                                extends AbstractController(cc) {
//
//
//  implicit val credentialFormat = CredentialFormat.restFormat
//
//  implicit val signUpFormat = Json.format[SignUp]
//
//  def signUp = Action.async(parse.json) { implicit request =>
//    request.body.validate[SignUp].map { signUp =>
//      val loginInfo = LoginInfo(CredentialsProvider.ID, signUp.email)
//      userService.retrieve(loginInfo).flatMap {
//        case None => /* user not already exists */
//          val user = User(None, loginInfo, signUp.email, signUp.firstName, signUp.lastName, RoleService.USER)
//          val password = passwordHasherRegistry.current.hash(signUp.password)
//          for {
//            userToSave <- userService.save(user)
//            authInfo <- authInfoRepository.add(loginInfo, password)
//            authenticator <- silhouette.env.authenticatorService.create(loginInfo)
//            token <- silhouette.env.authenticatorService.init(authenticator)
//            result <- silhouette.env.authenticatorService.embed(token,
//              Ok(Json.toJson(Token(token = token, expiresOn = authenticator.expirationDateTime)))
//            )
//          } yield {
//            result
//          }
//        case Some(_) => /* user already exists! */
//          Future(Conflict(Json.toJson(Bad(message = "user already exists"))))
//      }
//    }.recoverTotal {
//      case error =>
//        Future.successful(BadRequest(Json.toJson(Bad(message = JsError.toJson(error)))))
//    }
//  }
//
//  def authenticate = Action.async(parse.json[Credentials]) { implicit request =>
//    val credentials =
//      Credentials(request.body.identifier, request.body.password)
//    credentialsProvider
//      .authenticate(credentials)
//      .flatMap { loginInfo =>
//        userService.retrieve(loginInfo).flatMap {
//          case Some(user) =>
//            val config = configuration.underlying
//            silhouette.env.authenticatorService
//              .create(loginInfo)
//              .map {
//                case authenticator => authenticator
//              }
//              .flatMap { authenticator =>
//                silhouette.env.eventBus.publish(LoginEvent(user, request))
//                silhouette.env.authenticatorService
//                  .init(authenticator)
//                  .flatMap { token =>
//                    silhouette.env.authenticatorService
//                      .embed(
//                        token,
//                        Ok(
//                          Json.toJson(
//                            Token(
//                              token,
//                              expiresOn = authenticator.expirationDateTime
//                            )
//                          )
//                        )
//                      )
//                  }
//              }
//          case None =>
//            Future.failed(new IdentityNotFoundException("Couldn't find user"))
//        }
//      }
//      .recover {
//        case _: ProviderException =>
//          Forbidden
//      }
//  }
//
////  def list(pageNumber: Int, pageSize:Int) = Action.async { implicit request =>
////    userHandler.list(pageNumber, pageSize)
////      .map(users => if (users.isEmpty) NoContent else Ok(Json.toJson(users)))
////  }
////
////  def get(id: Long) = Action.async { implicit request =>
////    userHandler.get(id).map {
////      case user: Some[UserResource] => Ok(Json.toJson(user))
////      case None => NotFound
////    }
////  }
//
////  def listOfRequests(userId: Long, pageNumber: Int, pageSize: Int) = Action.async { implicit request =>
////      userDao.listOfRequests(userId, pageNumber, pageSize)
////        .map(requests => if (requests.isEmpty) NoContent else Ok(Json.toJson(requests)))
////  }
//
//}
//
//
//


