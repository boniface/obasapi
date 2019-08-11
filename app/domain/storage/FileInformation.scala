package domain.storage

import java.io.File

case class FileInformation(key: String, filename: String, contentType: Option[String], file: File, fileSize: Long, dispositionType: String)

