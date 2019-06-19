package domain.demographics

import play.api.libs.json.Json

case class Gender(
                   genderId: String,
                   name: String
                 )

object Gender {
  implicit val GenderFmt = Json.format[Gender]
}