package models

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

case class ServiceProperty(id: Long, serviceId: Long, propertyId: Long)

class ServicePropertyTable(tag: Tag) extends Table[ServiceProperty](tag, Some("public"), "service_property") {
  def id = column[Long]("id", O.PrimaryKey)
  def serviceId = column[Long]("service_id")
  def propertyId = column[Long]("property_id")
  def service = foreignKey("SERVICE_FK", serviceId, Tables.Service)(_.id)
  def property = foreignKey("PROPERTY_FK", propertyId, Tables.Property)(_.id)
  override def * : ProvenShape[ServiceProperty] = (id, serviceId, propertyId) <> (ServiceProperty.tupled, ServiceProperty.unapply)
}