package services.login.Impl

import domain.login.{Login, LoginToken, Register}
import play.api.mvc.Request
import services.login.LoginService

import scala.concurrent.Future

class LoginServiceImpl extends LoginService {
  override def isUserRegistered(user: Register): Future[Boolean] = ???

  override def forgotPassword(register: Register): Future[Boolean] = ???

  override def register(register: Register): Future[Boolean] = ???

  override def getLoginToken(login: Login): Future[Option[LoginToken]] = ???

  override def resetPasswordRequest(resetKey: String): Future[Boolean] = ???

  override def loginStatus[A](request: Request[A]): Future[Boolean] = ???
}



