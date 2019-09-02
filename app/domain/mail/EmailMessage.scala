package domain.mail

import play.api.libs.json.Json

case class EmailMessage( subject: String,
                         email: String,
                         content: String)

object EmailMessage{
  implicit val emMesg = Json.format[EmailMessage]
}

