package domain.application

import java.time.LocalDateTime

import akka.http.javadsl.model.DateTime
import play.api.libs.json.Json

case class ApplicationStatus (
                          applicationStatusId:String,
                          description:String,
                          date:LocalDateTime,
                        )
object ApplicationStatus{
  implicit val applicationStatusFmt = Json.format[ApplicationStatus]

}

