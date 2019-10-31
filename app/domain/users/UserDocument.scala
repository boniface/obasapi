package domain.users

import play.api.libs.json.Json

case class UserDocument(
                         userId:String,
                         documentId:String
                        )
object UserDocument{
  implicit val userDocumentFmt =Json.format[UserDocument]

}
