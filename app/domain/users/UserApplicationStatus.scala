package domain.users

import java.time.LocalDateTime

import play.api.libs.json.Json

case class UserApplicationStatus(
                                  statusId:String,
                                  applicationId:String,
                                  modifiedBy:String,
                                  dateTime: LocalDateTime,

                                )
object UserApplicationStatus{
  implicit val UserApplicationStatusFmt = Json.format[UserApplicationStatus]
}
