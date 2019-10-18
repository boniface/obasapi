package domain.users

import play.api.libs.json.Json

case class UserRelative(
                         userId: String,
                         name: String,
                         cellphone: String,
                         email: String = "",
                         relationship: String
                       )
object UserRelative{
  implicit val userRelativeFmt = Json.format[UserRelative]
}
