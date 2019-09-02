package domain.users

import play.api.libs.json.Json

case class UserDocuments(
                         userDocumentsId:String,
                         documentId:String
                        )
object UserDocuments{
  implicit val userDocuments =Json.format[UserDocuments]

}
