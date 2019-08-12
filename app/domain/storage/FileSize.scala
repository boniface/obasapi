package domain.storage

import play.api.libs.json.Json

case class FileSize(size: Int)

object FileSize {
  implicit lazy val respFMT = Json.format[FileSize]
}
