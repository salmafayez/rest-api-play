package models.tables

import enums.RoleService
import models.{Property, Service, ServiceProperty, User}
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape.proveShapeOf


trait DBTableDefinitions {
  self: HasDatabaseConfigProvider[PostgresProfile] =>

  class Users(tag: Tag) extends Table[User](_tableTag = tag, _schemaName = Some("play"), _tableName = "user") {

    implicit private val roleMapper = RoleService.mapper
    def id = column[Long]("id", O.PrimaryKey)
    def firstName = column[String]("first_name", O.Length(25))
    def lastName = column[String]("last_name", O.Length(25))
    def email = column[String]("email", O.Length(25))
    def password = column[Option[String]]("password", O.Length(25))
    def role = column[RoleService.Role]("role")
    override def * = (id.?, firstName, lastName, email, password,role) <> (User.tupled, User.unapply)
  }


  class Services(tag: Tag) extends Table[Service](_tableTag = tag, _schemaName = Some("play"), "service") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc, O.Unique)

    def name = column[String]("name", O.Length(25))

    override def *  = (id.?, name) <> (Service.tupled, Service.unapply)
  }
  class Properties(tag: Tag) extends Table[Property](tag, Some("play"), "PROPERTY") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name", O.Length(25))
    def location = column[String]("location", O.Length(25))
    override def * = (id.?, name, location) <> (Property.tupled, Property.unapply)
  }

  class ServiceProperties(tag: Tag) extends Table[ServiceProperty](tag, Some("PLAY"), "SERVICE_PROPERTY") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc, O.Unique)
    def serviceId = column[Long]("service_id")
    def propertyId = column[Long]("property_id")
    def service = foreignKey("SERVICE_FK", serviceId, services)(_.id)
    def property = foreignKey("PROPERTY_FK", propertyId, properties)(_.id)
    override def * = (id.?, serviceId, propertyId) <> (ServiceProperty.tupled, ServiceProperty.unapply)
  }

  val users = TableQuery[Users]
  val properties = TableQuery[Properties]
  val services = TableQuery[Services]
  val serviceProperties = TableQuery[ServiceProperties]
}