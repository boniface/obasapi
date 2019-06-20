package domain.users

import play.api.libs.json.Json

case class UserContacts(
                        userContactId:String,
                        cellNumber:String,
                        alternativeNumber:String,
                        alternativeEmail:String
                       )
object UserContacts{
  implicit val userContactsFmt =Json.format[UserContacts]
}
