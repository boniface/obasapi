package domain.users

import play.api.libs.json.Json

case class UserPassword(
                        userPasswordId:String,
                        password:String
                       )
object UserPassword{
  implicit val userPasswordFmt =Json.format[UserPassword]

}
