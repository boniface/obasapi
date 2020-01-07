package services.users

import domain.users.UserMatricInstitution
import services.CrudService
import services.users.Impl.UserMatricInstitutionServiceImpl

import scala.concurrent.Future

trait UserMatricInstitutionService extends CrudService[UserMatricInstitution] {
  def updateEntity(entity: UserMatricInstitution): Future[Option[UserMatricInstitution]]
}

object UserMatricInstitutionService {
  def apply: UserMatricInstitutionService = new UserMatricInstitutionServiceImpl()
}
