package domain.users

case class UserCommunication(
                            communicationId:String,
                            description:sting
                            )
object UserCommunication{
 implicit val UserCommunicationFmt = Json.format[UserCommunication]
}
