package controllers.login

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._


class LoginRouter@Inject()
(loginController: LoginController) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/isregistered") =>
      loginController.isUserRegistered
    case POST(p"/forgotpassword") =>
      loginController.forgotPassword
    case POST(p"/login") =>
      loginController.login
    case GET(p"/register") =>
      loginController.register
    case GET(p"/passwordreset/$resetkey") =>
      loginController.resetPassword(resetkey)
  }
}