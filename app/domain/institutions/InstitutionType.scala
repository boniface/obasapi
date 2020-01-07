package domain.institutions

import play.api.libs.json.Json

case class InstitutionType(id: String, name: String, description: Option[String])

object InstitutionType {
  implicit val institutionTypeFmt = Json.format[InstitutionType]
}
