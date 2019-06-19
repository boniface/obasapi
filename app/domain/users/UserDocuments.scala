package domain.users

case class UserDocuments(
                         UserDocumentsId:String,
                         documentId:String
                        )
object UserDocuments{
  implicit val UserDocuments =Json.format[UserDocuments]

}
