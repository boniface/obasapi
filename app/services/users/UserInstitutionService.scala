package services.users

import domain.users.UserInstitution
import services.CrudService
import services.users.Impl.cockroachdb

trait UserInstitutionService extends CrudService[UserInstitution]{

}

object UserInstitutionService{
  def roach: UserInstitutionService = new cockroachdb.UserInstitutionServiceImpl()
}
