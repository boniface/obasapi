package domain.location

import play.api.libs.json.Json

case class LocationType(
                        locationTypeId: String,
                        name: String,
                        code: String
                       )
object LocationType{
  implicit val locationTypeFmt = Json.format[LocationType]

}
