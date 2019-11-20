package domain.location

import play.api.libs.json.Json

case class Location(
                     locationId: String,
                     locationTypeId: String,
                     name: String,
                     latitude: String,
                     longitude: String,
                     locationParentId: Option[String]
                   )

object Location {
  implicit val locationFmt = Json.format[Location]

}
