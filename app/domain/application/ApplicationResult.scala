package domain.application

//import akka.http.scaladsl.model.DateTime
//import akka.http.scaladsl.model.DateTime
import java.time.LocalDateTime

import org.joda.time.DateTime
import play.api.libs.json.Json

case class ApplicationResult(
                             applicationResultId:String,
                             description:String,
                             date:LocalDateTime
                             )
object ApplicationResult{
  implicit val applicationResultFmt = Json.format[ApplicationResult]
}
