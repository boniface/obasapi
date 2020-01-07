package domain.users

import java.time.LocalDateTime

import play.api.libs.json.Json

case class UserApplication(
                                  userId: String,
                                  applicationId: String,
                                  dateTime: LocalDateTime = LocalDateTime.now
                          )
object UserApplication{
  implicit val userApplicationFmt = Json.format[UserApplication]

  implicit object localDateTime extends Ordering[LocalDateTime] {
    def compare(x: LocalDateTime, y: LocalDateTime): Int = y.compareTo(x)
  }

  val orderByDateTime: Ordering[UserApplication] = Ordering.by(uas => uas.dateTime)
}
