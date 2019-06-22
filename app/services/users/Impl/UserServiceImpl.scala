package services.users.Impl

import domain.login.PasswordChangeCredentials
import domain.mail.MessageResponse
import domain.users.{User, UserRole}
import domain.util.registration.Register
import services.users.UserService

import scala.concurrent.Future

class UserServiceImpl extends UserService {

  override def saveEntity(entity: User): Future[Boolean] = ???

  override def getEntities: Future[Seq[User]] = ???

  override def getEntity(id: String): Future[Option[User]] = ???

  override def deleteEntity(entity: User): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

  override def getSiteUsers(siteId: String): Future[Seq[User]] = ???

  override def getUserByEmail(email: String): Future[Seq[User]] = ???

  override def isUserAvailable(siteId: String, email: String): Future[Boolean] = ???

  override def registerUser(registration: Register): Future[MessageResponse] = ???

  override def createUser(user: User, userRole: UserRole): Future[MessageResponse] = ???

  override def createSiteAdmin(siteAdmin: User): Future[MessageResponse] = ???

  override def resetAccount(user: User): Future[MessageResponse] = ???

  override def changePassword(credentials: PasswordChangeCredentials): Future[Boolean] = ???

  override def requestNewPassword(token: String): Future[MessageResponse] = ???
}
