package domain.security

import play.api.libs.json.Json

case class ResetToken(resetokenvalue: String, email: String)

object ResetToken {

  implicit val tokenResets = Json.format[ResetToken]
}

