package services.login

import domain.login.{LoginCredential, LoginStatus, Profile}
import domain.mail.MessageResponse
import domain.users.User
import domain.util.login.UserGeneratedToken
import play.api.mvc.Request
import services.login.Impl.LoginServiceImpl

import scala.concurrent.Future

trait LoginService {
  def resetPasswordRequest(resetKey: String):Future[MessageResponse]
  def isUserRegistered(entity: User): Future[Boolean]
  def forgotPassword(profile:Profile): Future[MessageResponse]
  def getLoginToken(credential: LoginCredential, agent: String): Future[UserGeneratedToken]
  def checkLoginStatus[A](request: Request[A]): Future[LoginStatus]
}

object LoginService{
  def apply: LoginService = new LoginServiceImpl()
}
