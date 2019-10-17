package domain.users

import play.api.libs.json.Json

case class UserAddress(
                        userId: String,
                        addressTypeId: String,
                        address: String,
                        postalCode: String
                      )

object UserAddress {
  implicit val userAddressFmt = Json.format[UserAddress]
}



