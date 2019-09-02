package domain.demographics

import play.api.libs.json.Json

case class Roles(id: String,
                 roleName: String)

object Roles {

  implicit val rolesFmt = Json.format[Roles]

}
