package repository.users

import domain.users.UserDemographics
import repository.Repository
import repository.users.Impl.cockroachdb

trait UserDemographicsRepository extends Repository[UserDemographics]{

}

object UserDemographicsRepository{
  def roach: UserDemographicsRepository = new cockroachdb.UserDemographicsRepositoryImpl()
}
