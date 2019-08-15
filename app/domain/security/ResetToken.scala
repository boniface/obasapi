package domain.security

import play.api.libs.json.Json
import util.APPKeys

case class ResetToken(resetokenvalue: String, email: String, status: String = APPKeys.ACTIVE)

object ResetToken {

  implicit val tokenResets = Json.format[ResetToken]
}

