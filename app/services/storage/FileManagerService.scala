package services.storage

import java.io.File

import domain.storage.{FileData, FileSize}
import services.storage.Impl.FileManagerServiceImpl

import scala.concurrent.Future

trait FileManagerService {
  def postFile(file:File, fileName:String):Future[FileData]
  def getFile(uri:String ): Future[Either[String,File]]
  def deleteFile(uri: String):Future[FileSize]
}
object FileManagerService{
  def apply: FileManagerService = new FileManagerServiceImpl()
}

