package domain.users

import play.api.libs.json.Json

case class UserTertiarySubject(userId: String, applicationId: String, subjectId: String, subjectMark: Double)

object UserTertiarySubject {
  implicit val userTertiarySubjectFmt = Json.format[UserTertiarySubject]
}
