package repository.users

import domain.users.UserApplicationResult
import repository.Repository
import repository.users.Impl.cassandra.UserApplicationResultRepositoryImpl

trait UserApplicationResultRepository extends Repository[UserApplicationResult]{

}

object UserApplicationResultRepository{
  def apply: UserApplicationResultRepository = new UserApplicationResultRepositoryImpl()
}
