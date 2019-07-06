package repository.users

import domain.users.UserResults
import repository.Repository
import repository.users.impl.cockroachdb

trait UserResultsRepository extends Repository[UserResults]{

}

object UserResultsRepository{
  def roach: UserResultsRepository = new cockroachdb.UserResultsRepositoryImpl()
}
