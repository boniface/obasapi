package domain.documents



import java.time.LocalDateTime

import akka.http.javadsl.model
import akka.http.scaladsl.model.DateTime
import play.api.libs.json.Json

case class Document(
                    email:String,
                    documentsId: String,
                    documentTypeId:String,
                    description:String,
                    url: String,
                    mime: String,
                    date: LocalDateTime,
                    permission:String
                    )
object Document{
  implicit val documentFmt = Json.format[Document]

}
