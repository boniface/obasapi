package repository.users

import domain.users.UserTertiaryInstitution
import repository.Repository
import repository.users.impl.cockroachdb.UserTertiaryInstitutionRepositoryImpl

import scala.concurrent.Future

trait UserTertiaryInstitutionRepository extends Repository[UserTertiaryInstitution] {
  def getEntity(userId: String, applicationId: String): Future[Option[UserTertiaryInstitution]]
  def getEntitiesForUser(userId: String): Future[Seq[UserTertiaryInstitution]]
}

object UserTertiaryInstitutionRepository {
  def apply: UserTertiaryInstitutionRepository = new UserTertiaryInstitutionRepositoryImpl()
}
