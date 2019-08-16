package repository.users

import domain.users.UserDemographics
import repository.Repository
import repository.users.impl.cockroachdb.UserDemographicsRepositoryImpl

trait UserDemographicsRepository extends Repository[UserDemographics]{

}

object UserDemographicsRepository{
  def roach: UserDemographicsRepository = new UserDemographicsRepositoryImpl()
}
