package services.users

import domain.users.UserApplicationInstitution
import services.CrudService
import services.users.Impl.UserApplicationInstitutionServiceImpl

import scala.concurrent.Future

trait UserApplicationInstitutionService extends CrudService[UserApplicationInstitution] {
  def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationInstitution]]
  def getEntitiesForUser(userId: String): Future[Seq[UserApplicationInstitution]]
  def updateEntity(entity: UserApplicationInstitution): Future[Option[UserApplicationInstitution]]
}

object UserApplicationInstitutionService {
  def apply: UserApplicationInstitutionService = new UserApplicationInstitutionServiceImpl()
}
