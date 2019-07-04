package domain.users

import java.time.LocalDateTime

import akka.http.javadsl.model.DateTime
import play.api.libs.json.Json

case class User(
               email:String,
               firstName:String,
               middleName:String,
               lastName:String,
               dateOfBirth:LocalDateTime
               )
object User{
  implicit val userFmt = Json.format[User]

}
