package domain.demographics

import play.api.libs.json.Json

@Deprecated
case class DistrictTown(districtCode: String, townCode: String)

@Deprecated
object DistrictTown {
  implicit  val districtTownFmt = Json.format[DistrictTown]
}
