package enums

import play.api.libs.json.{Format, JsResult, JsValue, Json}
import slick.jdbc.PostgresProfile.api._

object StatusService extends Enumeration{
  type Status = Value
  val fulfilled = Value
  val cancelled = Value
  val pending = Value

  implicit val format = new Format[StatusService.Value] {
    override def writes(o: StatusService.Value): JsValue = Json.toJson(o.toString)
    override def reads(json: JsValue): JsResult[StatusService.Value] = json.validate[String].map(StatusService.withName)
  }

  val mapper = MappedColumnType.base[StatusService.Status, String](
    status => status.toString,
    string => StatusService.withName(string)
  )
}