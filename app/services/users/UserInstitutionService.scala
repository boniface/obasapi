package services.users

import domain.users.UserInstitution
import services.CrudService
import services.users.Impl.UserInstitutionServiceImpl

import scala.concurrent.Future

trait UserInstitutionService extends CrudService[UserInstitution]{

  def getEntity(userId: String, institutionId: String): Future[Option[UserInstitution]]

  def getEntitiesForUser(userId: String): Future[Seq[UserInstitution]]
}

object UserInstitutionService{
  def roach: UserInstitutionService = new UserInstitutionServiceImpl()
}
