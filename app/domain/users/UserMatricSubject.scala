package domain.users

import play.api.libs.json.Json

case class UserMatricSubject(userId: String, subjectId: String, subjectMark: Double)

object UserMatricSubject {
  implicit val userMatricSubjectFmt = Json.format[UserMatricSubject]
}
