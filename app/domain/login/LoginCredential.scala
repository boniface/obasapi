package domain.login

import play.api.libs.json.Json

case class LoginCredential(email:String ="", password:String="")

object LoginCredential {
  implicit val credentialFmt = Json.format[LoginCredential]
}
