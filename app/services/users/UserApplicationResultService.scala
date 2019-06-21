package services.users

import domain.users.UserApplicationResult
import services.CrudService
import services.users.Impl.UserApplicationResultServiceImpl

trait UserApplicationResultService extends CrudService[UserApplicationResult]{

}

object UserApplicationResultService{
  def apply: UserApplicationResultService = new UserApplicationResultServiceImpl()
}
