package domain.login

import play.api.libs.json.Json

case class Register(email: String)

object Register {
  implicit val regFmt = Json.format[Register]
}