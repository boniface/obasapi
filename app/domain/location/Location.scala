package domain.location

import play.api.libs.json.Json

case class Location(
                     locationId: String,
                     locationTypeId: String,
                     name: String,
                     latitude: String,
                     longitude: String,
                     locationParentId: String
                   )

object Location {
  implicit val locationFmt = Json.format[Location]

  val orderByName: Ordering[Location] = Ordering.by(location => location.name)

}
