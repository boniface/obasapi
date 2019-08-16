package domain.storage

import java.io.File

import play.api.libs.json.Json

case class FileInformation(key: String, filename: String, contentType: Option[String], file: File, fileSize: Long, dispositionType: String)

