package services

import com.mohiva.play.silhouette.api.LoginInfo
import models.User
import models.dao.UserDAO

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


class UserServiceImpl @Inject() (userDAO: UserDAO)(implicit ex: ExecutionContext) extends UserService {

  def retrieve(loginInfo: LoginInfo): Future[Option[User]] = userDAO.find(loginInfo)
  def save(user: User): Future[User] = userDAO.save(user)
  def update(user: User): Future[User] = userDAO.update(user)
}
