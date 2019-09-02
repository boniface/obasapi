package domain.mail

import java.time.LocalDateTime

import play.api.libs.json.Json

case class MailConfig(siteId: String="",
                 id: String="",
                 key: String="",
                 value: String="",
                 host: String="",
                 port: String="",
                 state: String="",
                 date: LocalDateTime= LocalDateTime.now())

object MailConfig {
  implicit val mailFmt = Json.format[MailConfig]

}
