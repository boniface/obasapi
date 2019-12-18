package services.users

import domain.users.UserMatricInstitution
import services.CrudService
import services.users.Impl.UserMatricInstitutionServiceImpl

trait UserMatricInstitutionService extends CrudService[UserMatricInstitution] {

}

object UserMatricInstitutionService {
  def apply: UserMatricInstitutionService = new UserMatricInstitutionServiceImpl()
}
