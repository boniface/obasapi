package domain.login

import play.api.libs.json.Json

case class LoginToken(email: String, token: String)

object LoginToken {
  implicit val tokenFmt = Json.format[LoginToken]
}

