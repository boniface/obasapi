package domain.institutions

import play.api.libs.json.Json

case class InstitutionContact(
                             institutionId: String,
                             contactTypeId: String,
                             contact: String
                             )

object InstitutionContact {
  implicit val institutionContactFmt = Json.format[InstitutionContact]
}