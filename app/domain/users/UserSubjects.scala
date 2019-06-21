package domain.users

import play.api.libs.json.Json

case class UserSubjects(
                   userSubjectId:String,
                   name:String,
                   description: String,
                   term:String
                  )
object UserSubjects{
  implicit val userSubjectsFmt = Json.format[UserSubjects]
}
