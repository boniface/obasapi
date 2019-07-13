package domain.login

import play.api.libs.json.Json

case class ResetKey(resetKey: String)

object ResetKey {

  implicit val keyResets = Json.format[ResetKey]
}