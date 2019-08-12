package io.storage


import java.io.File

import com.softwaremill.sttp.DeserializationError
import domain.storage.{FileMetaData, FileSize}
import io.storage.Impl.FileManagerRepositoryImpl

import scala.concurrent.Future

trait FileManagerRepository {
  type IOError = DeserializationError[io.circe.Error]
  def postFile(file: File): Future[Either[String, Either[IOError, FileMetaData]]]
  def getFile(uri:String ):Future[Either[String,File]]
  def deleteFile(uri:String): Future[Either[String, Either[IOError, FileSize]]]
}

object FileManagerRepository {
  def apply: FileManagerRepository = new FileManagerRepositoryImpl()
}
