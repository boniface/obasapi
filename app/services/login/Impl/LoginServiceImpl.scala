package services.login.Impl

import domain.login.{Login, LoginToken, Register}
import play.api.mvc.Request
import services.login.LoginService
import services.users.UserService

import scala.concurrent.Future

class LoginServiceImpl extends LoginService {
  override def isUserRegistered(user: Register): Future[Boolean] = {
    UserService.apply.isUserAvailable(user.email)
  }

  override def forgotPassword(register: Register): Future[Boolean] = ???

  override def register(register: Register): Future[Boolean] = ???

  override def getLoginToken(login: Login): Future[Option[LoginToken]] = ???

  override def resetPasswordRequest(resetKey: String): Future[Boolean] = ???

  override def checkLoginStatus[A](request: Request[A]): Future[Boolean] = ???

  override def logOut(register: Register): Future[Boolean] = ???
}



