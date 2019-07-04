package repository.users

import domain.users.UserApplicationResult
import repository.Repository
import repository.users.Impl.cockroachdb

trait UserApplicationResultRepository extends Repository[UserApplicationResult]{

}

object UserApplicationResultRepository{
  def roach: UserApplicationResultRepository = new cockroachdb.UserApplicationResultRepositoryImpl()
}
