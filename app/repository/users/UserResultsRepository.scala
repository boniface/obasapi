package repository.users

import domain.users.UserResults
import repository.Repository
import repository.users.Impl.cassandra.UserResultsRepositoryImpl

trait UserResultsRepository extends Repository[UserResults]{

}

object UserResultsRepository{
  def apply: UserResultsRepository = new UserResultsRepositoryImpl()
}
