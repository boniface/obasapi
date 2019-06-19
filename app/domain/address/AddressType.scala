package domain.address

import play.api.libs.json.Json

case class AddressType(
                  addressTypeID: String,
                  name: String
                 )
object AddressType{
  implicit val addressTypeFmt = Json.format[AddressType]
}