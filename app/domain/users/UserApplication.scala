package domain.users

import play.api.libs.json.Json

case class UserApplication(
                                  userId: String,
                                  applicationId: String
                          )
object UserApplication{
  implicit val userApplicationFmt = Json.format[UserApplication]
}
