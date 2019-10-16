package domain.users

import play.api.libs.json.Json

case class UserApplication(
                                 userApplicationResultId:String,
                                 description:String
                                )
object UserApplication{
  implicit val userApplicationResultFmt = Json.format[UserApplication]
}
