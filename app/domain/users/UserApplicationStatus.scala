package domain.users

import java.time.LocalDateTime

import play.api.libs.json.Json

case class UserApplicationStatus(
                                  applicationId:String,
                                  statusId:String,
                                  modifiedBy:String,
                                  dateTime: LocalDateTime,

                                )
object UserApplicationStatus{
  implicit val UserApplicationStatusFmt = Json.format[UserApplicationStatus]
}
