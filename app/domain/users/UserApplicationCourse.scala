package domain.users

import play.api.libs.json.Json

case class UserApplicationCourse(userId: String, applicationId: String, courseId: String)

object UserApplicationCourse {
  implicit val userApplicationCourseFmt = Json.format[UserApplicationCourse]
}
