package models

import com.mohiva.play.silhouette.api.util.PasswordInfo
import com.mohiva.play.silhouette.api.{Identity, LoginInfo}
import com.mohiva.play.silhouette.impl.providers.CredentialsProvider
import com.mohiva.play.silhouette.password.BCryptSha256PasswordHasher
import enums.RoleService

case class User(
  id: Option[Long] = Some(0),
  firstName: String,
  lastName: String,
  email: String,
  password: Option[String] = None,
  role: RoleService.Role = RoleService.USER) extends Identity {
  def loginInfo = LoginInfo(CredentialsProvider.ID, email)
  def passwordInfo = PasswordInfo(BCryptSha256PasswordHasher.ID, password.get)
}
