package domain.login


import java.time.LocalDateTime


import play.api.libs.json.Json

case class UserLoginEvents(email:String="",
                           id:String="",
                           date: LocalDateTime=LocalDateTime.now(),
                           description:String=UserState.NOTVALIDATED
                          )

object UserLoginEvents{
  implicit val userFmt = Json.format[UserLoginEvents]

}