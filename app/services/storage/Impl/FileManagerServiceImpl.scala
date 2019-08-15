package services.storage.Impl

import java.io.File
import java.util.UUID
import domain.storage.{FileData, FileMetaData, FileSize}
import io.storage.FileManagerRepository
import services.storage.FileManagerService
import util.HelperUtil
import util.connections.NetConnection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class FileManagerServiceImpl extends FileManagerService {
  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  def cdnUrl: String = NetConnection.cdnUrl


  override def postFile(file: File, fileName: String): Future[FileData] = FileManagerRepository.apply.postFile(file) map {
    case Right(fileResult) =>
      val fileMetaData = if (fileResult.isRight) fileResult.right.get else FileMetaData("", "", "", "", 0)
      val arr: Array[String] = fileMetaData.fid.split(",")
      FileData(HelperUtil.md5Hash(UUID.randomUUID().toString),
        cdnUrl + arr(0) + "/" + arr(1) + "/" + fileName,
        fileMetaData.eTag)
    case Left(error) => FileData(error, error, error)
  }

  override def getFile(uri: String): Future[Either[String, File]] = FileManagerRepository.apply.getFile(uri)

  override def deleteFile(uri: String): Future[FileSize] = FileManagerRepository.apply.deleteFile(uri) map {
    case Right(results) =>
      if (results.isRight) results.right.get  else FileSize(0)
    case Left(error) =>
      logger.info(s"count = $error, status = $error")
      FileSize(0)

  }
}
