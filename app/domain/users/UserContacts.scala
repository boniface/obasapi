package domain.users

import play.api.libs.json.Json

case class UserContacts(
                         userId: String,
                         contactTypeId:String,
                         contact: String
                       )

object UserContacts {
  implicit val userContactsFmt = Json.format[UserContacts]
}
