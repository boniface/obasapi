package services.users

import domain.users.UserTown
import services.CrudService
import services.users.Impl.UserTownServiceImpl

trait UserTownService extends CrudService[UserTown] {
}

object UserTownService {
  def apply: UserTownService = new UserTownServiceImpl()
}
