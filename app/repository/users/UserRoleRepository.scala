package repository.users

import domain.users.UserRole
import repository.Repository
import repository.users.impl.cockroachdb.UserRoleRepositoryImpl

trait UserRoleRepository extends Repository[UserRole]{

}

object UserRoleRepository{
  def roach: UserRoleRepository = new UserRoleRepositoryImpl()
}
