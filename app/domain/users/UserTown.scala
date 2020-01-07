package domain.users

import play.api.libs.json.Json

case class UserTown(userId: String, locationId: String)

object UserTown {
  implicit val userTownFmt = Json.format[UserTown]
}