package domain.login

import play.api.libs.json.Json

case class Login(email:String, password:String)

object Login{
  implicit val login = Json.format[Login]
}
