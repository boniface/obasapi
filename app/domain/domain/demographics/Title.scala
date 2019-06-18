package domain.demographics

import play.api.libs.json.Json

case class Title (
                   titleId: String,
                   name: String
                 )
object Title{
  implicit val titleFmt = Json.format[Title]

}
