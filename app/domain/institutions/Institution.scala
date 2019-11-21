package domain.institutions

import play.api.libs.json.Json

case class Institution(id: String,
                       institutionTypeId: String,
                       name: String
                      )

object Institution {
  implicit val institutionFmt = Json.format[Institution]
}