package domain.mail

import play.api.libs.json.Json



case class MailApi(id:String="Provider", key:String="",sender:String="")

object MailApi{
  implicit val mailFmt = Json.format[MailApi]
}
