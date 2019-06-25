package repository.demographics

import domain.demographics.Roles
import repository.Repository
import repository.demographics.Impl.RolesRepositoryImpl

trait RolesRepository extends Repository [Roles]{

}
object RolesRepository{

  def apply: RolesRepositoryImpl = new RolesRepositoryImpl()

}
