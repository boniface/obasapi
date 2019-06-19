package domain.demographics

import play.api.libs.json.Json

case class Gender(
                   genderId: String,
                   genderName: String
                 )

object Gender {
  implicit val genderFmt = Json.format[Gender]
}