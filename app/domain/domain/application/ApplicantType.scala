package domain.application

import play.api.libs.json.Json

case class ApplicantType(
                         ApplicantTypeId:String,
                         name:String
                         )
object ApplicantType{
  implicit val ApplicantTypeFmt = Json.format[ApplicantType]

}
