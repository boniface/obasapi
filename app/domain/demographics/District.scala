package domain.demographics

import play.api.libs.json.Json

@Deprecated
case class District(
                     districtCode: String,
                     districtName: String
                   )

object District {
  implicit val districFmt = Json.format[District]

}