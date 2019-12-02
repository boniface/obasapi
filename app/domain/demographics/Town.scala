package domain.demographics

import play.api.libs.json.Json

case class Town(
                 townCode: String,
                 townName: String
               )

object Town {
  implicit val townFmt = Json.format[Town]
}