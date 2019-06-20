package domain.subjects

import play.api.libs.json.Json

case class UniverityCourses(
                             courseCode:String,
                             description:Option[String],
                             name:String,
                             Type:String,
                             Term:String
                           )
object UniverityCourses{
  implicit val univerityCoursesFmt = Json.format[UniverityCourses]
}
