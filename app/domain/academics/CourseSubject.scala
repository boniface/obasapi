package domain.academics

import play.api.libs.json.Json

case class CourseSubject(courseId: String, subjectId: String)

object CourseSubject {
  implicit val courseSubjectFmt = Json.format[CourseSubject]
}
