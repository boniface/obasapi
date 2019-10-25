package domain.users

import play.api.libs.json.Json

case class UserCurrentLocation(

                              userId: String,
                              provinceCode: String,
                              districtCode: String,
                              townCode: String


                              )
object UserCurrentLocation {
  implicit val userCurrentLocationFmt = Json.format[UserCurrentLocation]
}
