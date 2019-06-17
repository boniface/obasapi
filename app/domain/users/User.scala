package domain.users

import play.api.libs.json.Json

case class User(
               email:String,
               firstName:String,
               middleName:String,
               lastName:String,

               )
object User{
  implicit val userFmt = Json.format[User]

}
