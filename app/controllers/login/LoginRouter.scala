package controllers.login

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._


class LoginRouter@Inject()
(loginController: LoginController) extends SimpleRouter {
  override def routes: Routes = {
    case POST(p"/isregistered") =>
      loginController.isUserRegistered
    case POST(p"/forgotpassword") =>
      loginController.forgotPassword
    case POST(p"/getlogintoken") =>
      loginController.getLoginToken
    case GET(p"/reset/$resetkey") =>
      loginController.resetPasswordRequest(resetkey)
//    case GET(p"/isavailable/$siteId/$email") =>
//      loginController.isUserAvailable(siteId,email)



  }
}