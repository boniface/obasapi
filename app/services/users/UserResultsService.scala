package services.users

import domain.users.UserResults
import services.CrudService
import services.users.Impl.cockroachdb

trait UserResultsService extends CrudService[UserResults]{

}

object UserResultsService{
  def roach: UserResultsService = new cockroachdb.UserResultsServiceImpl()
}
