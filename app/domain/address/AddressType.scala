package domain.address

import play.api.libs.json.Json

case class AddressType(
                  addressTypeID: String,
                  addressName: String
                 )

object AddressType{
  implicit val addressTypeFmt = Json.format[AddressType]
}