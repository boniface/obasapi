package services.login

import domain.login.{Login, LoginCredential, LoginStatus, LoginToken, Profile, Register}
import domain.mail.MessageResponse
import domain.users.User
import domain.util.login.UserGeneratedToken
//import io.MetaHeaders
import play.api.mvc.Request
import services.login.Impl.LoginServiceImpl

import scala.concurrent.Future

trait LoginService {
  def isUserRegistered(user:Register): Future[Boolean]
  def forgotPassword(register:Register): Future[Boolean]
  def register(register:Register): Future[Boolean]
  def getLoginToken(login: Login): Future[Option[LoginToken]]
  def resetPasswordRequest(resetKey: String):Future[Boolean]
  def loginStatus[A](request: Request[A]): Future[Boolean]
}

object LoginService{
  def apply: LoginService = new LoginServiceImpl()
}
