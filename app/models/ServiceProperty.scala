package models

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

case class ServiceProperty(id: Option[Long], serviceId: Long, propertyId: Long)

