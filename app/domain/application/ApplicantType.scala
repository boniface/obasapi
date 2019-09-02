package domain.application

import play.api.libs.json.Json

case class ApplicantType(
                         applicantTypeId:String,
                         name:String
                         )
object ApplicantType{
  implicit val applicantTypeFmt = Json.format[ApplicantType]

}
