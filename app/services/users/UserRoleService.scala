package services.users

import domain.users.UserRole
import services.CrudService
import services.users.Impl.cockroachdb

trait UserRoleService extends CrudService[UserRole]{

}

object UserRoleService{
  def roach: UserRoleService = new cockroachdb.UserRoleServiceImpl()
}
