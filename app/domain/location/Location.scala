package domain.location

import play.api.libs.json.Json

case class Location(
                     locationId:String,
                     name:String,
                     latitude:String,
                     longitude:String,
                     code:String,
                     locationTypeId:String
                   )
object Location{
  implicit val locationFmt = Json.format[Location]

}
