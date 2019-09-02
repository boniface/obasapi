package controllers.storage

import java.io.File
import java.net.URLConnection
import java.nio.file.attribute.PosixFilePermission._
import java.nio.file.attribute.PosixFilePermissions
import java.nio.file.{Files, Path}
import java.util
import io.circe.generic.auto._
import akka.stream.IOResult
import akka.stream.scaladsl._
import akka.util.ByteString
import controllers.ApiResponse
import domain.storage.{FileData, FileInformation, FileSize}
import javax.inject.Inject
import org.apache.commons.io.FileUtils
import play.api.libs.streams._
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc._
import play.core.parsers.Multipart.FileInfo
import services.login.LoginService
import services.storage.FileManagerService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class FileManagerController @Inject()(cc: MessagesControllerComponents, api: ApiResponse)

  extends MessagesAbstractController(cc) {
  def className: String = "FileManagerController"

  def domainService: FileManagerService = FileManagerService.apply

  def loginService: LoginService = LoginService.apply


  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  type FilePartHandler[A] = FileInfo => Accumulator[ByteString, FilePart[A]]

  def upload: Action[MultipartFormData[File]] = Action.async(parse.multipartFormData(handleFilePartAsFile)) {
    implicit request: MessagesRequest[MultipartFormData[File]] =>
      def  uploadedFile: Option[FileInformation] = request.body.file("upload").map {
        case FilePart(key, filename, contentType, file, fileSize, dispositionType) =>
          FileInformation(key, filename, contentType, file, fileSize, dispositionType)
      }
      val response: Future[FileData] = {

        for {
          authorize <- loginService.checkLoginStatus(request) if authorize
          checkSize <- loginService.checkFileSize(uploadedFile.get.fileSize) if checkSize
          results <- domainService.postFile(uploadedFile.get.file, uploadedFile.get.filename)
        } yield results
      }
      api.requestResponse[FileData](response, className)
  }
  def deleteFile(vol:String,fid:String,filename:String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      val response: Future[FileSize] = {
        val url = "/" + vol + "/" + "/" + fid + "/" + filename

        for {
          authorize <- loginService.checkLoginStatus(request) if authorize
          results <- domainService.deleteFile(url)
        } yield results
      }
      api.requestResponse[FileSize](response, className)
  }


  def getFile(vol: String, fid: String, filename: String): Action[AnyContent] = Action.async {
    val url = "/" + vol + "/" + "/" + fid + "/" + filename
    val mimeType = URLConnection.getFileNameMap.getContentTypeFor(new File(filename).getName)
    domainService.getFile(url) map {
      case Right(file) => {
        val dataContent: Source[ByteString, _] = StreamConverters.fromInputStream(() => FileUtils.openInputStream(file))
        Ok.chunked(dataContent).as(mimeType)
      }
      case Left(err) => {
        logger.info(s"Error = $err, status = $err")
        NotFound
      }
    }
  }

  def getFileSize(vol: String, fid: String, filename: String, height: String, width: String): Action[AnyContent] = Action.async {
    val url = "/" + vol + "/" + "/" + fid + "/" + filename + "?height=" + height + "&width=" + width
    lazy val mimeType: String = URLConnection.getFileNameMap.getContentTypeFor(new File(filename).getName)
    domainService.getFile(url) map {
      case Right(file) => {
        val dataContent: Source[ByteString, _] = StreamConverters.fromInputStream(() => FileUtils.openInputStream(file))
        Ok.chunked(dataContent).as(mimeType)
      }
      case Left(err) => {
        logger.info(s"Error = $err, status = $err")
        NotFound
      }
    }
  }

  private def handleFilePartAsFile: FilePartHandler[File] = {
    case FileInfo(partName, filename, contentType, dispositionType) =>
      val attr = PosixFilePermissions.asFileAttribute(util.EnumSet.of(OWNER_READ, OWNER_WRITE))
      val path: Path = Files.createTempFile("multipartBody", "tempFile", attr)
      Accumulator(FileIO.toPath(path)).map {
        case IOResult(count, status) =>
          logger.info(s"count = $count, status = $status")
          FilePart(partName, filename, contentType, path.toFile, count, dispositionType)
      }
  }
}

