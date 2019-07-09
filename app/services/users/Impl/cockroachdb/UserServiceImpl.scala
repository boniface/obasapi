package services.users.Impl.cockroachdb

import domain.login.PasswordChangeCredentials
import domain.mail.MessageResponse
import domain.users.{User, UserRole}
import domain.util.registration.Register
import repository.users.UserRepository
import services.users.UserService

import scala.concurrent.Future

class UserServiceImpl extends UserService {

  override def saveEntity(entity: User): Future[Boolean] =
    UserRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[User]] =
    UserRepository.roach.getEntities

  override def getEntity(id: String): Future[Option[User]] =
    UserRepository.roach.getEntity(id)

  override def deleteEntity(entity: User): Future[Boolean] =
    UserRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserRepository.roach.createTable

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
