package domain.users

import play.api.libs.json.Json

case class UserMatricInstitution(userId: String, institutionId: String)

object UserMatricInstitution{
  implicit val userMatricInstitutionFmt = Json.format[UserMatricInstitution]
}