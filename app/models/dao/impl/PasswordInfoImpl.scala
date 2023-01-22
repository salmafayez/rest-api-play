package models.dao.impl

import com.google.inject.Inject
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO
import models.dao.{DAOSlick, UserDAO}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.PostgresProfile

import scala.concurrent.{ExecutionContext, Future}
import scala.reflect.ClassTag


class PasswordInfoImpl @Inject() (userDAO: UserDAO)
                                 (implicit val classTag: ClassTag[PasswordInfo], ec: ExecutionContext)
  extends DelegableAuthInfoDAO[PasswordInfo] {

  override def find(loginInfo: LoginInfo): Future[Option[PasswordInfo]] =
    userDAO.find(loginInfo).map(_.map(_.passwordInfo))

  override def add(loginInfo: LoginInfo, passwordInfo: PasswordInfo): Future[PasswordInfo] =
    update(loginInfo, passwordInfo)

  override def update(loginInfo: LoginInfo, passwordInfo: PasswordInfo): Future[PasswordInfo] = userDAO.find(loginInfo).flatMap {
    case Some(user) => userDAO.update(user.copy(password = Some(passwordInfo.password))).map(_.passwordInfo)
    case None => Future.failed(new Exception("user not found"))
  }
  override def save(loginInfo: LoginInfo, passwordInfo: PasswordInfo): Future[PasswordInfo] =
    update(loginInfo, passwordInfo)
  override def remove(loginInfo: LoginInfo): Future[Unit] =
    update(loginInfo, PasswordInfo("", "")).map(_ => ())

}

