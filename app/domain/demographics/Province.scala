package domain.demographics

import play.api.libs.json.Json

@Deprecated
case class Province(
                     provinceCode: String,
                     provinceName: String
                   )

@Deprecated
object Province {
  implicit val provinceFmt = Json.format[Province]

}
