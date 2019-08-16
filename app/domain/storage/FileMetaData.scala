package domain.storage

import play.api.libs.json.Json

case class FileMetaData(eTag:String,fid:String,fileName:String,fileUrl:String,size:Int)

object FileMetaData{
  implicit lazy val metadataFMT = Json.format[FileMetaData]
}
