package domain.users

import play.api.libs.json.Json

case class UserDemographics(
                            userDemographicsId:String,
                            genderId:String,
                            raceId:String
                           )
object UserDemographics{
  implicit val userDemographicsFmt =Json.format[UserDemographics]
}
