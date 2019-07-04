package services.users

import domain.users.UserDemographics
import services.CrudService
import services.users.Impl.cockroachdb

trait UserDemographicsService extends CrudService[UserDemographics]{

}

object UserDemographicsService{
  def roach: UserDemographicsService = new cockroachdb.UserDemographicsServiceImpl()
}
