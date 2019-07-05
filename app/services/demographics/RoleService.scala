package services.demographics

import domain.demographics.Roles
import services.CrudService
import services.demographics.Impl.cockroachdb

trait RoleService  extends CrudService[Roles]{

}

object RoleService {
  def roach: RoleService = new cockroachdb.RoleServiceImpl
}
