package domain.academics

import play.api.libs.json.Json

case class Course(id: String, courseName: String, courseDesc: Option[String])

object Course {
  implicit val courseFmt = Json.format[Course]
}