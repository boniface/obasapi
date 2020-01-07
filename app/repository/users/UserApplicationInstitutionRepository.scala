package repository.users

import domain.users.UserApplicationInstitution
import repository.Repository
import repository.users.impl.cockroachdb.UserApplicationInstitutionRepositoryImpl

import scala.concurrent.Future

trait UserApplicationInstitutionRepository extends Repository[UserApplicationInstitution]{
  def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationInstitution]]
  def getEntitiesForUser(userId: String): Future[Seq[UserApplicationInstitution]]
}

object UserApplicationInstitutionRepository {
  def apply: UserApplicationInstitutionRepository = new UserApplicationInstitutionRepositoryImpl()
}
