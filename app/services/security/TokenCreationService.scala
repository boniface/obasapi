package services.security

import domain.users.User
import services.security.Impl.TokenCreationServiceImpl

import scala.concurrent.Future

trait TokenCreationService {

  def generateLoginToken(user: User, role: String): Future[String]

}

object TokenCreationService {
  def apply: TokenCreationService = new TokenCreationServiceImpl()
}
