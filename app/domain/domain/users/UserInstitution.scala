package domain.users

import play.api.libs.json.Json

case class UserInstitution(
                      userInstitutionId:String,
                      name:String
                     )
object UserInstitution {
  implicit val UserInstitutionFmt = Json.format[UserInstitution]
}

