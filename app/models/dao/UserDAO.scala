package models.dao

import com.mohiva.play.silhouette.api.LoginInfo
import models.User

import scala.concurrent.Future

/**
 * Gives access to the user storage.
 */
trait UserDAO extends DAOSlick {
  def find(loginInfo: LoginInfo): Future[Option[User]]
  def save(user: User): Future[User]
  def update(user: User): Future[User]
}
