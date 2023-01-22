package unused.dtos

import play.api.libs.json.{Format, Json}

case class ServicePropertyDto(serviceId: Long, propertyId: Long)

object ServicePropertyDto{
  implicit val format: Format[ServicePropertyDto] = Json.format
}