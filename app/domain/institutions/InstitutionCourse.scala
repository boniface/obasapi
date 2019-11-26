package domain.institutions

import play.api.libs.json.Json

case class InstitutionCourse(institutionId: String, courseId: String)

object InstitutionCourse {
  implicit val institutionCourseFmt = Json.format[InstitutionCourse]
}
