package domain.institutions

import play.api.libs.json.Json

case class InstitutionAddress(
                             institutionId: String,
                             addressTypeId: String,
                             address: String,
                             postalCode: String
                             )

object InstitutionAddress {
  implicit val institutionAddressFmt = Json.format[InstitutionAddress]
}