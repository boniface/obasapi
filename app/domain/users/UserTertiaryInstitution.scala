package domain.users

import play.api.libs.json.Json

case class UserTertiaryInstitution(userId: String, applicationId: String, institutionId: String, debtAmount: Double)

object UserTertiaryInstitution {
  implicit val userTertiaryInstitutionFmt = Json.format[UserTertiaryInstitution]
}
