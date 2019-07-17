package domain.users

import play.api.libs.json.Json

case class UserRole(
                     userId: String,
                     roleId: String
                   )

object UserRole {
  implicit val userRoleFmt = Json.format[UserRole]
}