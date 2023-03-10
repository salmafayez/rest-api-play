package enums

import play.api.libs.json.{Format, JsResult, JsValue, Json}
import slick.jdbc.PostgresProfile.api._

object RatingService extends Enumeration{
  type Rating = Value
  val bad = Value
  val good = Value
  val excellent = Value

  implicit val format = new Format[RatingService.Value] {
    override def writes(o: RatingService.Value): JsValue = Json.toJson(o.toString)

    override def reads(json: JsValue): JsResult[RatingService.Value] = json.validate[String].map(RatingService.withName)

  }
  val mapper = MappedColumnType.base[RatingService.Rating, String](
    rating => rating.toString,
    string => RatingService.withName(string)
  )
}
