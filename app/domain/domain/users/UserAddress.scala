package domain.users

import play.api.libs.json.Json

case class UserAddress(
                       userAddressId:String,
                       physicalAddress:String,
                       postalCode:String
                      )

object UserAddress{
  implicit val UserAddressFmt = Json.format[UserAddress]
}



