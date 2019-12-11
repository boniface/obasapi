package domain.users

import play.api.libs.json.Json

case class UserInstitution(
                      userId: String,
                      institutionId: String,
                      debtAmount: Double = 0d,
                      isCurrent: Boolean = false
                     )
object UserInstitution {
  implicit val userInstitutionFmt = Json.format[UserInstitution]
}

