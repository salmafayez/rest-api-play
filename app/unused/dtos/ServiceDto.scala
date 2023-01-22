package unused.dtos

import play.api.libs.json.{Format, Json}

case class ServiceDto(name: String)

object ServiceDto{
  implicit val format: Format[ServiceDto] = Json.format
}