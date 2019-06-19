package domain.institutions

import play.api.libs.json.Json

case class School(
                  schoolId:String,
                  schoolName:String,
                  schoolDetails:Map[String, String],
                  schoolState:String
                 )
object School{
  implicit val schoolFmt = Json.format[School]

}
