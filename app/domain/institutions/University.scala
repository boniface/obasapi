package domain.institutions

import play.api.libs.json.Json

case class University(
                  universityId:String,
                  universityName:String,
                  universityProvince:String,
                  universityPhoneNumber:String,
                  universityEmail:String
                )
object University {
  implicit val universityFmt = Json.format[University]

}
