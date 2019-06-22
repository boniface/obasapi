package domain.util.registration

import play.api.libs.json.Json

case class Register(
                     email: String
                   )

object Register {
  implicit val registFmt = Json.format[Register]

}
