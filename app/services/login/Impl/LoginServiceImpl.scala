package services.login.Impl

import domain.login.{LoginCredential, LoginStatus, Profile}
import domain.mail.MessageResponse
import domain.users.User
import domain.util.login.UserGeneratedToken
import play.api.mvc.Request
import services.login.LoginService

import scala.concurrent.Future

class LoginServiceImpl extends LoginService {
  override def resetPasswordRequest(resetKey: String): Future[Boolean] = {

  }

  override def isUserRegistered(entity: User): Future[Boolean] = ???

  override def forgotPassword(profile: Profile): Future[MessageResponse] = ???

  override def getLoginToken(credential: LoginCredential, agent: String): Future[UserGeneratedToken] = ???

  override def checkLoginStatus[A](request: Request[A]):
    Future[LoginStatus] = ???
}

