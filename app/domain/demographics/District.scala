package domain.demographics

import play.api.libs.json.Json

case class District(
                     districtCode: String,
                     districtName: String
                   )

object District {
  implicit val districFmt = Json.format[District]

}