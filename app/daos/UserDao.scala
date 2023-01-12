package daos

import models.{ServiceRequest, Tables, User}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.Results.Created
import play.db.NamedDatabase
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class UserDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                       (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[PostgresProfile] {

  def insert(user: User) =
    db.run(Tables.User += user).map { _ => Created("")}

  def list(pageNumber: Int, pageSize: Int): Future[Seq[User]] =
    db.run(Tables.User.drop(pageNumber * pageSize).take(pageSize).result)


  def listOfRequests(userId: Long, pageNumber: Int, pageSize: Int): Future[Seq[ServiceRequest]] = {
    val query = Tables.ServiceRequest
      .join(Tables.User)
      .on(_.creatorId === _.id)
      .filter(_._2.id === userId)
      .map(_._1)
      .drop(pageNumber * pageSize)
      .take(pageSize)

    db.run(query.result)
  }


}
