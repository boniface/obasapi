package domain.users

import play.api.libs.json.Json

case class UserSubject(userId: String, institutionId: String, subjectId: String, subjectMark: Double)

object UserSubject {
  implicit val userSubjectFmt = Json.format[UserSubject]
}
