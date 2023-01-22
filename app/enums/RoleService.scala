package enums

import play.api.libs.json.{Format, JsResult, JsValue, Json}
import slick.jdbc.PostgresProfile.api._

object RoleService extends Enumeration{
  type Role = Value
  val ADMIN, USER = Value

  implicit val format = new Format[RoleService.Value] {
    override def writes(o: RoleService.Value): JsValue = Json.toJson(o.toString)
    override def reads(json: JsValue): JsResult[RoleService.Value] = json.validate[String].map(RoleService.withName)
  }

  val mapper = MappedColumnType.base[RoleService.Role, String](
    status => status.toString,
    string => RoleService.withName(string)
  )

}
//object Roles {
//  sealed abstract class Role(val name: String)
//  case object AdminRole extends Role("admin")
//  case object UserRole extends Role("user")
//}