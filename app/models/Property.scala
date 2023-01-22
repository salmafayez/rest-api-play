package models

import slick.lifted.ProvenShape
import slick.jdbc.PostgresProfile.api._

case class Property(id: Option[Long], name: String, location: String)
