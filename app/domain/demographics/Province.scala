package domain.demographics

import play.api.libs.json.Json

case class Province(
                     provinceCode: String,
                     provinceName: String
                   )

object Province {
  implicit val provinceFmt = Json.format[Province]

}
