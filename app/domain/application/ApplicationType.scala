package domain.application

import play.api.libs.json.Json

case class ApplicationType(id: String, name: String, description: Option[String])

object ApplicationType {
  implicit val applicationTypeFmt = Json.format[ApplicationType]
}
