package domain.subjects

import play.api.libs.json.Json

case class MatricSubjects(
                          subjectCode:String,
                          description:Option[String],
                          name:String,
                          Term:String
                         )
object MatricSubjects{
  implicit val matricSubjectsFmt = Json.format[MatricSubjects]
}
