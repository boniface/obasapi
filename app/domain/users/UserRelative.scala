package domain.users

import play.api.libs.json.Json

case class UserRelative(
                        userRelativeId:String,
                        name:String,
                        cellphone:String,
                        relationship:String,
                        email:String
                       )
object UserRelative{
  implicit val userRelativeFmt = Json.format[UserRelative]
}
