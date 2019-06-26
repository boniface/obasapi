package repository.users

import domain.users.UserRole
import repository.Repository
import repository.users.Impl.cassandra.UserRoleRepositoryImpl

trait UserRoleRepository extends Repository[UserRole]{

}

object UserRoleRepository{
  def apply: UserRoleRepository = new UserRoleRepositoryImpl()
}
