package domain.users

import play.api.libs.json.Json

case class UserCommunication(
                            communicationId:String,
                            description:String
                            )
object UserCommunication{
 implicit val userCommunicationFmt = Json.format[UserCommunication]
}
