package domain.util.login

import play.api.libs.json.Json

case class UserGeneratedToken(token: String="",
                              status: String="",
                              message: String="")

object UserGeneratedToken {
  implicit val tokenForm = Json.format[UserGeneratedToken]
}
