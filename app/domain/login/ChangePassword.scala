package domain.login

import java.time.LocalDateTime

import play.api.libs.json.Json

case class ChangePassword(
                           email: String,
                           oldPassword: String,
                           newPassword: String,
                           datetimeChanged: LocalDateTime = LocalDateTime.now
                         )

object ChangePassword {
  implicit val changePasswordFmt = Json.format[ChangePassword]
}
