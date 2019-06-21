package services.users

import domain.users.UserResults
import services.CrudService
import services.users.Impl.UserResultsServiceImpl

trait UserResultsService extends CrudService[UserResults]{

}

object UserResultsService{
  def apply: UserResultsService = new UserResultsServiceImpl()
}
