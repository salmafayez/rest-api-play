package utilities

import enums.{RatingService, RoleService, StatusService}
import slick.jdbc.PostgresProfile.api._

object Mapper {
  val statusMapper = MappedColumnType.base[StatusService.Status, String](
    status => status.toString,
    string => StatusService.withName(string)
  )

  val ratingMapper = MappedColumnType.base[RatingService.Rating, String](
    rating => rating.toString,
    string => RatingService.withName(string)
  )

  val roleMapper = MappedColumnType.base[RoleService.Role, String](
    role => role.toString,
    string => RoleService.withName(string)
  )
}
