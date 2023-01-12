package models

import enums.RoleService
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import utilities.Mapper

case class User(id: Long, username: String, password: String, role: RoleService.Role)

class UserTable(tag: Tag) extends Table[User](tag, Some("public"), "user") {

  implicit val roleMapper = Mapper.roleMapper
  def id = column[Long]("id", O.PrimaryKey)
  def username = column[String]("username", O.Length(25))
  def password = column[String]("password")
  def role = column[RoleService.Role]("role")
  override def * : ProvenShape[User] = (id, username, password, role) <> (User.tupled, User.unapply)
}
