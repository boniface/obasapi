package domain.mail

import play.api.libs.json.Json

case class SmtpConfig(id:String="",
                      port: Int = 587,
                      host: String = "smtp.gmail.com",
                      username: String="",
                      password: String="")

object SmtpConfig {
  implicit val smtpFmt = Json.format[SmtpConfig]

}
