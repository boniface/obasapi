package domain.application

import play.api.libs.json.Json

case class Application(
                        id: String,
                        applicationTypeId: String,
                        applicantTypeId: String,
                        institutionId: String,
                        courseId: String
                      )

object Application {
  implicit val applicationFmt = Json.format[Application]
}