package domain.documents

import java.time.LocalDateTime

import play.api.libs.json.Json

case class DocumentStatus(
                           documentId: String,
                           statusId: String,
                           modifiedBy: String,
                           comment: Option[String],
                           dateTime: LocalDateTime = LocalDateTime.now
                         )

object DocumentStatus{
  implicit val documentStatusFmt = Json.format[DocumentStatus]

  implicit object localDateTime extends Ordering[LocalDateTime] {
    def compare(x: LocalDateTime, y: LocalDateTime): Int = y.compareTo(x)
  }

  val orderByDateTime: Ordering[DocumentStatus] = Ordering.by(uas => uas.dateTime)
}
