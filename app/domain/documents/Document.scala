package domain.documents

import java.time.LocalDateTime
import play.api.libs.json.Json

case class Document(
                    documentId: String,
                    documentTypeId:String,
                    description:String,
                    url: String,
                    mime: String,
                    date: LocalDateTime,
                    permission:String,
                    documentStatus: String
                    )
object Document{
  implicit val documentFmt = Json.format[Document]

}
