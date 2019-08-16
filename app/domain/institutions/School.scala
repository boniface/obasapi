package domain.institutions

import play.api.libs.json.Json

case class School(
                  schoolId:String,
                  schoolName:String,
                  schoolAddress:String,
                  schoolProvince:String,
                  schoolPhonenumber:String,

                 )
object School{
  implicit val schoolFmt = Json.format[School]

}
