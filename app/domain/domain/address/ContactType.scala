package domain.address

import play.api.libs.json.Json

case class ContactType(
                       ContactType:String,
                       name:String
                      )
object ContactType{
  implicit  val ContactTypFm = Json.format[ContactType]
}
