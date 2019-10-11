package domain.users

import java.time.LocalDateTime

import play.api.libs.json.Json

case class User(
               email:String,
               idNumber: String = "",
               firstName:String = "",
               middleName:String = "",
               lastName:String = "",
               dateOfBirth:LocalDateTime = LocalDateTime.now
               )
object User{
  implicit val userFmt = Json.format[User]

}
