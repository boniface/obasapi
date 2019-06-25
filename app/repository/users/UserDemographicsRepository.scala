package repository.users

import domain.users.UserDemographics
import repository.Repository
import repository.users.Impl.cassandra.UserDemographicsRepositoryImpl

trait UserDemographicsRepository extends Repository[UserDemographics]{

}

object UserDemographicsRepository{
  def apply: UserDemographicsRepository = new UserDemographicsRepositoryImpl()
}
