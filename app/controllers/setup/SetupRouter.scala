package controllers.setup

import controllers.mail.MailApiController
import controllers.setup.db.CockroachSetupController
import controllers.setup.security.ApiKeysController
import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class SetupRouter @Inject()  (cockroachSetupController: CockroachSetupController,
                              apiKeysController: ApiKeysController,
                              mailApiController: MailApiController) extends SimpleRouter {

  override def routes: Routes =  {

    // Routers for Cockroach setup
    case GET(p"/db/cockroach/tables/create") =>
      cockroachSetupController.createTables
    case GET(p"/db/cockroach/tables/loadlookup") =>
      cockroachSetupController.loadData
    case GET(p"/security/apikey/$phrase") =>
      apiKeysController.create(phrase)
  }
}
