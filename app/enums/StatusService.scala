package enums

import play.api.libs.json.{Format, JsResult, JsValue, Json}

object StatusService extends Enumeration{
  type Status = Value
  val fulfilled = Value
  val cancelled = Value
  val pending = Value

  implicit val format = new Format[StatusService.Value] {
    override def writes(o: StatusService.Value): JsValue = Json.toJson(o.toString)
    override def reads(json: JsValue): JsResult[StatusService.Value] = json.validate[String].map(StatusService.withName)
  }
}