package domain.users

import play.api.libs.json.Json

case class UserCourse(userId: String, institutionId: String, courseId: String)

object UserCourse {
  implicit val userCourseFmt = Json.format[UserCourse]
}
