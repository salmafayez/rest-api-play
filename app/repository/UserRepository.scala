package repository
//
//import dtos.UserDto
//import unused.models.{ServiceRequest, Tables, User}
//import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
//import slick.jdbc.PostgresProfile
//import slick.jdbc.PostgresProfile.api._
//
//import javax.inject.{Inject, Singleton}
//import scala.concurrent.Future
//
//
//trait UserRepository {
//  def create(userDto: UserDto): Future[Int]
//  def list(pageNumber: Int, pageSize: Int): Future[Seq[User]]
//  def get(id: Long): Future[Option[User]]
//  def listOfRequests(userId: Long, pageNumber: Int, pageSize: Int): Future[Seq[ServiceRequest]]
//}
//
//@Singleton
//class UserRepositoryImpl @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
//                              (implicit executionContext: RepositoryExecutionContext)
//  extends UserRepository
//  with HasDatabaseConfigProvider[PostgresProfile] {
//
//  def create(userDto: UserDto) = {
//    val user = User(0, userDto.username, userDto.password, userDto.role)
//    db.run(Tables.User += user)
//  }
//
//  def list(pageNumber: Int, pageSize: Int): Future[Seq[User]] =
//    db.run(Tables.User.drop(pageNumber * pageSize).take(pageSize).result)
//
//  def listOfRequests(userId: Long, pageNumber: Int, pageSize: Int): Future[Seq[ServiceRequest]] = {
//    val query = Tables.ServiceRequest
//      .join(Tables.User)
//      .on(_.creatorId === _.id)
//      .filter(_._2.id === userId)
//      .map(_._1)
//      .drop(pageNumber * pageSize)
//      .take(pageSize)
//
//    db.run(query.result)
//  }
//
//  override def get(id: Long): Future[Option[User]] =
//    db.run(Tables.User.filter(_.id === id).result.headOption)
//}
