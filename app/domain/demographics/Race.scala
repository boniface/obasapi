package domain.demographics

import play.api.libs.json.Json

case class Race(raceId: String,
                raceName: String
          )
object Race{
  implicit val raceFmt = Json.format[Race]
}

