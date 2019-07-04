package repository.users

import domain.users.UserRole
import repository.Repository
import repository.users.Impl.cockroachdb

trait UserRoleRepository extends Repository[UserRole]{

}

object UserRoleRepository{
  def roach: UserRoleRepository = new cockroachdb.UserRoleRepositoryImpl()
}
