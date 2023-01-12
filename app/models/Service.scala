package models

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
case class Service(id: Long, name: String)

class ServiceTable(tag: Tag) extends Table[Service](tag, Some("public"), "service") {
  def id = column[Long]("id", O.PrimaryKey)
  def name = column[String]("name", O.Length(25))
  override def * : ProvenShape[Service] = (id, name) <> (Service.tupled, Service.unapply)
}
