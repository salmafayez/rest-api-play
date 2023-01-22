package unused

import com.mohiva.play.silhouette.api.{Identity, LoginInfo}
import enums.RoleService
import play.api.libs.json._

import scala.util.{Failure, Success, Try}

case class User(id: Option[String], loginInfo: LoginInfo, email: String,
                firstName: String, lastName: String, role: RoleService.Role) extends Identity

object User {

  implicit val reader =
    Json.reads[User]
  implicit val writer = Json.writes[User]

  implicit val loginInfoReader = Json.reads[LoginInfo]
  implicit val loginInfoWriter = Json.writes[LoginInfo]

  implicit object UserWrites extends OWrites[User] {
    def writes(user: User): JsObject =
      user.id match {
        case Some(id) =>
          Json.obj(
            "_id" -> user.id,
            "loginInfo" -> Json.obj(
              "providerID" -> user.loginInfo.providerID,
              "providerKey" -> user.loginInfo.providerKey
            ),
            "email" -> user.email,
            "firstName" -> user.firstName,
            "lastName" -> user.lastName,
            "role" -> user.role
          )
        case _ =>
          Json.obj(
            "loginInfo" -> Json.obj(
              "providerID" -> user.loginInfo.providerID,
              "providerKey" -> user.loginInfo.providerKey
            ),
            "email" -> user.email,
            "firstName" -> user.firstName,
            "lastName" -> user.lastName,
            "role" -> user.role
          )
      }

    implicit object UserReads extends Reads[User] {
      def reads(json: JsValue): JsResult[User] = json match {
        case user: JsObject =>
          Try {
            val id = (user \ "_id" \ "$oid").asOpt[String]

            val providerId = (user \ "loginInfo" \ "providerID").as[String]
            val providerKey = (user \ "loginInfo" \ "providerKey").as[String]

            val email = (user \ "email").as[String]
            val firstName = (user \ "firstName").as[String]
            val lastName = (user \ "lastName").as[String]
            val role = (user \ "role").as[RoleService.Role]

            JsSuccess(
              new User(
                id,
                new LoginInfo(providerId, providerKey),
                email,
                firstName,
                lastName,
                role
              )
            )
          } match {
            case Success(value) => value
            case Failure(cause) => JsError(cause.getMessage)
          }
        case _ => JsError("expected.jsobject")
      }
    }

  }

}
