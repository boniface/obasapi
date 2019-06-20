package domain.users

import play.api.libs.json.Json

case class UserDocuments(
                         UserDocumentsId:String,
                         documentId:String
                        )
object UserDocuments{
  implicit val userDocuments =Json.format[UserDocuments]

}
