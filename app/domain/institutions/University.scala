package domain.institutions

import play.api.libs.json.Json

class University(
                  UniversityId:String,
                  UniversityName:String,
                  UniversityDetails:Map[String, String],
                  UniversityState:String
                )
object University {
  implicit val universityFmt = Json.format[University]

}

