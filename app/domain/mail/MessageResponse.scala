package domain.mail

import play.api.libs.json.Json

case class MessageResponse(statusCode: Int, headers: Map[String, String], body: String)

object MessageResponse {
  implicit val messageFmt = Json.format[MessageResponse]
}
