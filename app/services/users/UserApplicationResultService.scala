package services.users

import domain.users.UserApplicationResult
import services.CrudService
import services.users.Impl.cockroachdb

trait UserApplicationResultService extends CrudService[UserApplicationResult]{

}

object UserApplicationResultService{
  def roach: UserApplicationResultService = new cockroachdb.UserApplicationResultServiceImpl()
}
