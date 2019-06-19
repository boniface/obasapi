package domain.documents

import akka.http.javadsl.model.DateTime
import play.api.libs.json.Json

case class Document(
                    email:String,
                    DocumentsId: String,
                    documentTypeId:String,
                    description:String,
                    url: String,
                    mime: String,
                    date: DateTime,
                    permission: Set[String],
                    )
object Document{
  implicit val DocumentFmt = Json.format[Document]

}
