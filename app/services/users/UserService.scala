package services.users

import domain.users.{User, UserPassword}
import services.CrudService
import services.users.Impl.UserServiceImpl

import scala.concurrent.Future

trait UserService extends CrudService[User]{
  def isUserAvailable(email:String): Future[Boolean]
  def changePassword(credentials:UserPassword):Future[Boolean]
}

object UserService{
  def apply: UserService = new UserServiceImpl()
}
