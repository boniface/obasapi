package services.users

import domain.users.UserApplication
import services.CrudService
import services.users.Impl.UserApplicationServiceImpl

trait UserApplicationService extends CrudService[UserApplication]{

}

object UserApplicationService{
  def roach: UserApplicationService = new UserApplicationServiceImpl()
}
