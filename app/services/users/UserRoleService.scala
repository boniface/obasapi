package services.users

import domain.users.UserRole
import services.CrudService
import services.users.Impl.UserRoleServiceImpl

trait UserRoleService extends CrudService[UserRole]{

}

object UserRoleService{
  def apply: UserRoleService = new UserRoleServiceImpl()
}
