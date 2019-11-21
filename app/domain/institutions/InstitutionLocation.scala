package domain.institutions

import play.api.libs.json.Json

case class InstitutionLocation(
                              institutionId: String,
                              locationId: String,
                              longitude: String,
                              latitude: String
                              )

object InstitutionLocation {
  implicit val institutionLocationFmt = Json.format[InstitutionLocation]
}
