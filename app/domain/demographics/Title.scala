package domain.demographics

import play.api.libs.json.Json

case class Title (
                   titleId: String,
                   titlename: String
                 )
object Title{
  implicit val titleFmt = Json.format[Title]

}
