package domain.users

import java.time.LocalDate

import org.joda.time.LocalDateTime
import play.api.libs.json.Json

case class User(
               email:String,
               firstName:String,
               middleName:String,
               lastName:String,
               dateOfBirth:LocalDate
               )
object User{
  implicit val userFmt = Json.format[User]

}
