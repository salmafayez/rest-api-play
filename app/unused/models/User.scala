package unused.models

//import com.mohiva.play.silhouette.api.Identity
//import enums.RoleService
//import slick.lifted.ProvenShape
//import slick.jdbc.PostgresProfile.api._
//
//case class User(id: Long,
//                password: String,
//                username: String,
//                role: RoleService.Role) extends Identity
//class UserTable(tag: Tag) extends Table[User](tag, Some("public"), "user") {
//
//  implicit val roleMapper = Mapper.roleMapper
//
//  def id = column[Long]("id", O.PrimaryKey)
//
//  def username = column[String]("username", O.Length(25))
//
//  def password = column[String]("password")
//
//  def role = column[RoleService.Role]("role")
//
//  override def * : ProvenShape[User] = (id, username, password, role) <> (User.tupled, User.unapply)
//}
