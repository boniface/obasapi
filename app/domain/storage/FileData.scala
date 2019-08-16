package domain.storage

import play.api.libs.json.Json

case class FileData(id:String, url:String, etag:String)

object FileData{
  implicit lazy val fileDataFMT = Json.format[FileData]
}
