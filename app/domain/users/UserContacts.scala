package domain.users

case class UserContacts(
                        userContactId:String,
                        cellNumber:String,
                        alternativeNumber:String,
                        alternativeEmail:String
                       )
object UserContacts{
  implicit val UserContactsFmt =Json.format[UserContacts]
}
