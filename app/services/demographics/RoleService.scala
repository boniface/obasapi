package services.demographics

import domain.demographics.Roles
import services.CrudService
import services.demographics.Impl.cockroachdb.RoleServiceImpl

trait RoleService  extends CrudService[Roles]{

}

object RoleService {
  def roach: RoleService = new RoleServiceImpl
}
