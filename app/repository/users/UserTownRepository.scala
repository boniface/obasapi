package repository.users

import domain.users.UserTown
import repository.Repository
import repository.users.impl.cockroachdb.UserTownRepositoryImpl

trait UserTownRepository extends Repository[UserTown] {

}

object UserTownRepository {
  def apply: UserTownRepository = new UserTownRepositoryImpl()
}
