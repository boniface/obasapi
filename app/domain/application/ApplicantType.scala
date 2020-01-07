package domain.application

import play.api.libs.json.Json

case class ApplicantType(
                          id:String,
                          name: String,
                          description: Option[String]
                         )
object ApplicantType{
  implicit val applicantTypeFmt = Json.format[ApplicantType]

}
