package domain.users

import play.api.libs.json.Json

case class UserTertiaryCourse(userId: String, applicationId: String, courseId: String)

object UserTertiaryCourse {
  implicit val userTertiaryCourseFmt = Json.format[UserTertiaryCourse]
}
