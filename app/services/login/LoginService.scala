package services.login

import domain.login.{Login, LoginToken, Register}
import domain.security.ResetToken
import play.api.mvc.Request
import services.login.Impl.LoginServiceImpl

import scala.concurrent.Future

trait LoginService {
  def isUserRegistered(user: Register): Future[Boolean]

  def forgotPassword(register: Register): Future[Option[ResetToken]]

  def register(register: Register): Future[Boolean]

  def getLoginToken(login: Login): Future[Option[LoginToken]]

  def resetPasswordRequest(resetKey: String): Future[Boolean]

  def checkLoginStatus[A](request: Request[A]): Future[Boolean]

  def checkFileSize(size:Long):Future[Boolean]

  def logOut(register: Register): Future[Boolean]
}
object LoginService{
  def apply: LoginService = new LoginServiceImpl()
}
