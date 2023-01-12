package models

import slick.lifted.ProvenShape
import slick.jdbc.PostgresProfile.api._

case class Property(id: Long, name: String, location: String)

class PropertyTable(tag: Tag) extends Table[Property](tag, Some("public"), "property") {
  def id = column[Long]("id", O.PrimaryKey)
  def name = column[String]("name", O.Length(25))
  def location = column[String]("location", O.Length(25))
  override def * : ProvenShape[Property] = (id, name, location) <> (Property.tupled, Property.unapply)
}