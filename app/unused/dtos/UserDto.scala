package unused.dtos

import enums.RoleService
import play.api.libs.json.{Format, Json}

case class UserDto(username: String, password: String, role: RoleService.Role)

object UserDto{
  implicit val format: Format[UserDto] = Json.format
}