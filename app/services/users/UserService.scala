package services.users

import domain.login.PasswordChangeCredentials
import domain.mail.MessageResponse
import domain.users.{User, UserRole}
import domain.util.registration.Register
import services.CrudService
import services.users.Impl.cockroachdb

import scala.concurrent.Future

trait UserService extends CrudService[User]{
  def getSiteUsers(siteId: String): Future[Seq[User]]
  def getUserByEmail(email: String): Future[Seq[User]]
  def isUserAvailable(siteId:String, email:String): Future[Boolean]
  def registerUser(registration: Register): Future[MessageResponse]
  def createUser(user: User, userRole: UserRole):Future[MessageResponse]
  def createSiteAdmin(siteAdmin: User): Future[MessageResponse]

  def resetAccount(user:User):Future[MessageResponse]
  def changePassword(credentials:PasswordChangeCredentials):Future[Boolean]
  def requestNewPassword(token:String):Future[MessageResponse]


}

object UserService{
  def roach: UserService = new cockroachdb.UserServiceImpl()
}
