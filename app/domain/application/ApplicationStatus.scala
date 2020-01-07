package domain.application

import java.time.LocalDateTime

import play.api.libs.json.Json

case class ApplicationStatus(
                              applicationId: String,
                              statusId: String,
                              modifiedBy: String,
                              comment: Option[String],
                              dateTime: LocalDateTime = LocalDateTime.now
                            )

object ApplicationStatus {

  implicit val UserApplicationStatusFmt = Json.format[ApplicationStatus]

  implicit object localDateTime extends Ordering[LocalDateTime] {
    def compare(x: LocalDateTime, y: LocalDateTime): Int = y.compareTo(x)
  }

  val orderByDateTime: Ordering[ApplicationStatus] = Ordering.by(uas => uas.dateTime)
}
