package domain.academics

import play.api.libs.json.Json

case class Subject(id: String, subjectName: String, subjectDesc: Option[String])

object Subject {
  implicit val subjectFmt = Json.format[Subject]
}