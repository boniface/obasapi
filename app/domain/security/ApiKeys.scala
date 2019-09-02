package domain.security

import java.time.LocalDateTime

import play.api.libs.json.Json

case class ApiKeys (id:String,
                    value:String,
                    status:String,
                    date:LocalDateTime)

object ApiKeys{
  implicit val keysFmt = Json.format[ApiKeys]
}
