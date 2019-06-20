package domain.location

import play.api.libs.json.Json

case class Location(
                     locationId:String,
                     name:String,
                     latititude:String,
                     longitude:String,
                     code:String,
                     locationTypeId:String
                   )
object Location{
  implicit val locationFmt = Json.format[Location]

}
