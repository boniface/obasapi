package domain.login

import play.api.libs.json.Json

case class PasswordChangeCredentials(email:String ="",
                                     oldPassword:String="",
                                     newPassword:String=""){
  require(newPassword.nonEmpty)
}

object PasswordChangeCredentials {
  implicit val credentialFmt = Json.format[PasswordChangeCredentials]
}
