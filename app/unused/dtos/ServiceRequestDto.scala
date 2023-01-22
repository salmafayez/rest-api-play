package unused.dtos

import enums.{RatingService, StatusService}
import play.api.libs.json.{Format, Json}

import java.time.LocalDateTime

case class ServiceRequestDto(creatorId: Long, review: Option[String], rating: Option[RatingService.Rating],
                       deliveryTime: LocalDateTime, status: StatusService.Status, servicePropertyId: Long)
object ServiceRequestDto{
  implicit val format: Format[ServiceRequestDto] = Json.format
}