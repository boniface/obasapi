package services.users.Impl.cockroachdb

import domain.login.PasswordChangeCredentials
import domain.mail.MessageResponse
import domain.users.{User, UserRole}
import domain.util.registration.Register
import services.users.UserService

import scala.concurrent.Future

class UserServiceImpl extends UserService {

  override def saveEntity(entity: User): Future[Boolean] =
    UserService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[User]] =
    UserService.roach.getEntities

  override def getEntity(id: String): Future[Option[User]] =
    UserService.roach.getEntity(id)

  override def deleteEntity(entity: User): Future[Boolean] =
    UserService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserService.roach.createTable

  override def getSiteUsers(siteId: String): Future[Seq[User]] =
    UserService.roach.getSiteUsers(siteId)

  override def getUserByEmail(email: String): Future[Seq[User]] =
    UserService.roach.getUserByEmail(email)

  override def isUserAvailable(siteId: String, email: String): Future[Boolean] =
    UserService.roach.isUserAvailable(siteId,email)

  override def registerUser(registration: Register): Future[MessageResponse] =
    UserService.roach.registerUser(registration)

  override def createUser(user: User, userRole: UserRole): Future[MessageResponse] =
    UserService.roach.createUser(user,userRole)

  override def createSiteAdmin(siteAdmin: User): Future[MessageResponse] =
    UserService.roach.createSiteAdmin(siteAdmin)

  override def resetAccount(user: User): Future[MessageResponse] =
    UserService.roach.resetAccount(user)

  override def changePassword(credentials: PasswordChangeCredentials): Future[Boolean] =
    UserService.roach.changePassword(credentials)

  override def requestNewPassword(token: String): Future[MessageResponse] =
    UserService.roach.requestNewPassword(token)
}
