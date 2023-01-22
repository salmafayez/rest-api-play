package models.dao.impl

import com.google.inject.Inject
import com.mohiva.play.silhouette.api.LoginInfo
import models.User
import models.dao.UserDAO
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}


class UserDAOImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)
                            (implicit ec: ExecutionContext) extends UserDAO {

  override def find(loginInfo: LoginInfo): Future[Option[User]] = db.run {
    users.filter(_.email === loginInfo.providerKey).result.headOption
  }

  override def save(user: User): Future[User] = db.run {
    users returning users += user
  }
  override def update(user: User): Future[User] = db.run {
    users.filter(_.email === user.email).update(user).map(_ => user)
  }

}
