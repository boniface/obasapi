package domain.demographics

import play.api.libs.json.Json

case class ProvinceDistrict(provinceCode: String, districtCode: String)

object ProvinceDistrict {
  implicit val provinceDistrictFmt = Json.format[ProvinceDistrict]
}
