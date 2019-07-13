package services.users

import domain.login.Register
import domain.users.{User, UserPassword, UserRole}
import services.CrudService
import services.users.Impl.UserServiceImpl

import scala.concurrent.Future

trait UserService extends CrudService[User]{
  def isUserAvailable(email:String): Future[Boolean]
  def registerUser(registration: Register): Future[Boolean]
  def createUser(user: User, userRole: UserRole):Future[Boolean]
  def changePassword(credentials:UserPassword):Future[Boolean]
}

object UserService{
  def apply: UserService = new UserServiceImpl()
}
