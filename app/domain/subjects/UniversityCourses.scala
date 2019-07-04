package domain.subjects

import play.api.libs.json.Json

case class UniversityCourses(
                             courseCode:String,
                            //subjectCode?
                             description:Option[String],
                             name:String,
                             Type:String,
                             Term:String
                           )
object UniversityCourses{
  implicit val univerityCoursesFmt = Json.format[UniversityCourses]
}
