package domain.demographics

import play.api.libs.json.Json

@Deprecated
case class District(
                     districtCode: String,
                     districtName: String
                   )

@Deprecated
object District {
  implicit val districFmt = Json.format[District]

}