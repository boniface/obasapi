package domain.users

import play.api.libs.json.Json

case class UserApplicationInstitution(userId: String, applicationId: String, institutionId: String)

object UserApplicationInstitution {
  implicit val userApplicationInstitutionFmt = Json.format[UserApplicationInstitution]
}
