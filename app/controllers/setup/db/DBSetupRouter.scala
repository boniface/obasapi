package controllers.setup.db

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

class DBSetupRouter @Inject() (cockroachSetupController: CockroachSetupController) extends SimpleRouter {
  override def routes: Routes = {

    // Routers for Cockroach setup
    case GET(p"/cockroach/tables/create") =>
      println("creating tables...")
      cockroachSetupController.createTables
  }
}
