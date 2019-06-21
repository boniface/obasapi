package controllers.mail

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class MailRouter@Inject()
(mailConfigController: MailConfigController,
 smtpConfigController: SmtpConfigController,
 mailApiController: MailApiController) extends SimpleRouter {
  override def routes: Routes = {
    //MAIL
    case GET(p"/all") =>
      mailConfigController.getEntities
    case GET(p"/get/$id") =>
      mailConfigController.getEntity(id)
    case POST(p"/create") =>
      mailConfigController.create
    case POST(p"/update") =>
      mailConfigController.update
    case POST(p"/delete") =>
      mailConfigController.deleteEntity


    //SMTP
    case GET(p"/smtp/all") =>
      smtpConfigController.getEntities
    case GET(p"/smtp/get/$id") =>
      smtpConfigController.getEntity(id)
    case POST(p"/smtp/create") =>
      smtpConfigController.create
    case POST(p"/smtp/update") =>
      smtpConfigController.update
    case POST(p"/smtp/delete") =>
      smtpConfigController.deleteEntity

    //MAILAPI
    case GET(p"/api/all") =>
      mailApiController.getEntities
    case GET(p"/api/get/$id") =>
      mailApiController.getEntity(id)
    case POST(p"/api/create") =>
      mailApiController.create
    case POST(p"/api/update") =>
      mailApiController.update
    case POST(p"/api/delete") =>
      mailApiController.deleteEntity
    case POST(p"/api/send") =>
      mailApiController.sendMail

  }
}