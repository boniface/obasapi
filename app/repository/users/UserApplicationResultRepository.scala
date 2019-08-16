package repository.users

import domain.users.UserApplicationResult
import repository.Repository
import repository.users.impl.cockroachdb.UserApplicationResultRepositoryImpl

trait UserApplicationResultRepository extends Repository[UserApplicationResult]{

}

object UserApplicationResultRepository{
  def roach: UserApplicationResultRepository = new UserApplicationResultRepositoryImpl()
}
