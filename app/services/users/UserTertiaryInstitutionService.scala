package services.users

import domain.users.UserTertiaryInstitution
import services.CrudService
import services.users.Impl.UserTertiaryInstitutionServiceImpl

import scala.concurrent.Future

trait UserTertiaryInstitutionService extends CrudService[UserTertiaryInstitution] {
  def getEntity(userId: String, applicationId: String): Future[Option[UserTertiaryInstitution]]
  def getEntitiesForUser(userId: String): Future[Seq[UserTertiaryInstitution]]
}

object UserTertiaryInstitutionService {
  def apply: UserTertiaryInstitutionService = new UserTertiaryInstitutionServiceImpl()
}
