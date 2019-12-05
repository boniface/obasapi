package domain.users

import java.time.LocalDateTime

import play.api.libs.json.Json

case class UserApplicationStatus(
                                  applicationId:String,
                                  statusId:String,
                                  modifiedBy:String,
                                  comment: Option[String],
                                  dateTime: LocalDateTime = LocalDateTime.now

                                )
object UserApplicationStatus{

  implicit val UserApplicationStatusFmt = Json.format[UserApplicationStatus]

  implicit object localDateTime extends Ordering[LocalDateTime] {
    def compare(x: LocalDateTime, y: LocalDateTime): Int = y.compareTo(x)
  }

  val orderByDateTime: Ordering[UserApplicationStatus] = Ordering.by(uas => uas.dateTime)
}
