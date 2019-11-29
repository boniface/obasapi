package repository.users

import domain.users.UserApplicationStatus
import repository.Repository
import repository.users.impl.cockroachdb.UserApplicationStatusRepositoryImpl

trait UserApplicationStatusRepository extends Repository[UserApplicationStatus]{


}
object UserApplicationStatusRepository {
  def roach: UserApplicationStatusRepository =new UserApplicationStatusRepositoryImpl()
}
