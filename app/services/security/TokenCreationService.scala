package services.security

import domain.users.User

import scala.concurrent.Future

trait TokenCreationService {

  def generateLoginToken(user: User, role: String): Future[String]

}
