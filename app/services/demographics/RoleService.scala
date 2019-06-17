package services.demographics

import domain.demographics.Roles
import services.CrudService
import services.demographics.Impl.RoleServiceImpl

trait RoleService  extends CrudService[Roles]{

}

object RoleService {
  def apply: RoleService = new RoleServiceImpl()
}
