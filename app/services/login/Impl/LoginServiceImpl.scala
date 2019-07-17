package services.login.Impl

import com.typesafe.config.ConfigFactory
import domain.login.{Login, LoginToken, Register}
import play.api.mvc.Request
import services.login.{LoginService, LoginTokenService}
import services.users.UserService
import util.APPKeys

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class LoginServiceImpl extends LoginService {
  def isSecurityEnabled: Boolean = ConfigFactory.load().getBoolean("token-security.enabled")

  override def isUserRegistered(user: Register): Future[Boolean] = {
    UserService.apply.isUserAvailable(user.email)
  }

  override def forgotPassword(register: Register): Future[Boolean] = ???

  override def register(register: Register): Future[Boolean] = {
    //check if user is available
    // generated password
    // hash passwrd
    // save the user
    // create role
    //save hashed
    //save the role
    // get Email Message
    // send email
    true
  }

  override def getLoginToken(login: Login): Future[Option[LoginToken]] = {

    // //get hashpassword
    // compare
    // Get The User Role
    // Generate the Token
    // Save Token in LoginToken
  }

  override def resetPasswordRequest(resetKey: String): Future[Boolean] = ???

  override def checkLoginStatus[A](request: Request[A]): Future[Boolean] = {
    val token = request.headers.get(APPKeys.AUTHORIZATION).getOrElse("")
    val email = LoginTokenService.apply.getUserEmail(token)
    if (isSecurityEnabled) {

      if (LoginTokenService.apply.isTokenValid(token).isRight) {
        for {
          token <- LoginTokenService.apply.getEntity(email)
          // Might need to create a cache if Speed become an Issue
        } yield token.isDefined
      } else Future.successful(false)
    } else Future.successful(true)
  }

  override def logOut(register: Register): Future[Boolean] = {
    val emailToken = LoginToken(register.email, "")

    LoginTokenService.apply.deleteEntity(emailToken)
  }
}



