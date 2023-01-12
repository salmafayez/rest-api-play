package enums

import play.api.libs.json.{Format, JsResult, JsValue, Json}

object RoleService extends Enumeration{
  type Role = Value
  val admin, user = Value

  implicit val format = new Format[RoleService.Value] {
    override def writes(o: RoleService.Value): JsValue = Json.toJson(o.toString)
    override def reads(json: JsValue): JsResult[RoleService.Value] = json.validate[String].map(RoleService.withName)
  }
}
