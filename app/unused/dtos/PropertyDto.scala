package unused.dtos

import play.api.libs.json.{Format, Json}

case class PropertyDto(name: String, location: String)

object PropertyDto{
  implicit val format: Format[PropertyDto] = Json.format
}