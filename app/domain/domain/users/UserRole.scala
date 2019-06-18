package domain.users

import play.api.libs.json.Json

case class UserRole(
                     userRoleId: String,
                     name: String,
                     description: String
                   )

object UserRole {
  implicit val UserRoleFmt = Json.format[UserRole]
}