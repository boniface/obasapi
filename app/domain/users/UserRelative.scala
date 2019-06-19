package domain.users

import play.api.libs.json.Json

case class UserRelative(
                        userRelativeId:String,
                        name:String,
                        celphone:String,
                        relationship:String,
                        email:String
                       )
object UserRelative{
  implicit val UserRelativeFmt = Json.format[UserRelative]
}
