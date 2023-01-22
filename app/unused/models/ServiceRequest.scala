package unused.models

import enums.{RatingService, StatusService}
import slick.lifted.ProvenShape
import slick.jdbc.PostgresProfile.api._
import java.time.LocalDateTime

//case class ServiceRequest(id: Long, creatorId: Long, review: String, rating: RatingService.Rating,
//                          deliveryTime: LocalDateTime, status: StatusService.Status, servicePropertyId: Long)
//class ServiceRequestTable(tag: Tag) extends Table[ServiceRequest](tag, Some("public"), "service_request") {
//
////  implicit val ratingMapper = Mapper.ratingMapper
////  implicit val statusMapper = Mapper.statusMapper
//
////  def id = column[Long]("id", O.PrimaryKey)
////  def creatorId = column[Long]("creator_id")
////  def review = column[String]("review")
////  def rating = column[RatingService.Rating]("rating")
////  def deliveryTime = column[LocalDateTime]("delivery_time")
////  def status = column[StatusService.Status]("status")
//  def servicePropertyId = column[Long]("service_property_id")
////  def creator = foreignKey("CREATOR_FK", creatorId, Tables.User)(_.id)
////  def serviceProperty = foreignKey("SERVICE_PROPERTY_FK", servicePropertyId, Tables.ServiceProperty)(_.id)
//  override def * : ProvenShape[ServiceRequest] = (id, creatorId, review, rating, deliveryTime, status, servicePropertyId) <> (ServiceRequest.tupled, ServiceRequest.unapply)
//}
