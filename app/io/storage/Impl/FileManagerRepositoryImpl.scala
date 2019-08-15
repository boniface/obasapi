package io.storage.Impl

import java.io.File
import java.nio.file.Files

import com.softwaremill.sttp._
import com.softwaremill.sttp.asynchttpclient.future.AsyncHttpClientFutureBackend
import com.softwaremill.sttp.circe._
import domain.storage.{FileMetaData, FileSize}
import io.circe.generic.auto._
import io.storage.FileManagerRepository
import util.connections.NetConnection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class FileManagerRepositoryImpl extends FileManagerRepository {
  implicit val sttpBackend: SttpBackend[Future, Nothing] = AsyncHttpClientFutureBackend()
  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  def  masterUrl: String = NetConnection.masterUrl
  def volumeUrl: String = NetConnection.volumeUrl



 override def deleteFile(uri:String): Future[Either[String, Either[IOError, FileSize]]] ={
    val url = volumeUrl+uri
    sttp
      .delete(uri"$url")
      .response(asJson[FileSize])
      .send()
      .map (result => result.body)
  }

  override def postFile(file: File): Future[Either[String, Either[IOError, FileMetaData]]] ={
    val url = masterUrl+"/submit"
    sttp
      .post(uri"$url")
      .multipartBody(multipartFile("file",file))
      .response(asJson[FileMetaData])
      .send()
      .map { response =>
      response.body
    }
  }
  override def getFile(uri:String ): Future[Either[String,File]] ={
    val url = volumeUrl+uri
    val file = Files.createTempFile("file", "ext")
    sttp
      .get(uri"$url")
      .response(asFile(file.toFile,overwrite = true))
      .send()
      .map (result => result.body)
  }



}
