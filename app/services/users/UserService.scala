package services.users

import domain.users.User
import services.CrudService
import services.users.Impl.UserServiceImpl

trait UserService extends CrudService[User]{

}

object UserService{
  def apply: UserService = new UserServiceImpl()
}
