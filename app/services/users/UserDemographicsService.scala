package services.users

import domain.users.UserDemographics
import services.CrudService
import services.users.Impl.UserDemographicsServiceImpl

trait UserDemographicsService extends CrudService[UserDemographics]{

}

object UserDemographicsService{
  def roach: UserDemographicsService = new UserDemographicsServiceImpl()
}
