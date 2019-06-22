package domain.login

import java.time.LocalDateTime

import play.api.libs.json.Json

case class UserLoginToken(email:String="",
                          id: String="",
                          expiryDate:LocalDateTime=LocalDateTime.now,
                          tokenValue: String="",
                          message:String="")

object UserLoginToken {
  implicit val tokenFmt = Json.format[UserLoginToken]
  }
