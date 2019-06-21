package services.users

import domain.users.UserInstitution
import services.CrudService
import services.users.Impl.UserInstitutionServiceImpl

trait UserInstitutionService extends CrudService[UserInstitution]{

}

object UserInstitutionService{
  def apply: UserInstitutionService = new UserInstitutionServiceImpl()
}
