package domain.application

import java.time.LocalDateTime

import akka.http.javadsl.model.DateTime
import play.api.libs.json.Json

case class ApplicationStatus (
                               id:String,
                               name: String,
                               description: Option[String]
                        )
object ApplicationStatus{
  implicit val applicationStatusFmt = Json.format[ApplicationStatus]

}

