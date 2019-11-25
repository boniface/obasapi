package repository.users

import domain.users.UserInstitution
import repository.Repository
import repository.users.impl.cockroachdb.UserInstitutionRepositoryImpl

import scala.concurrent.Future

trait UserInstitutionRepository extends Repository[UserInstitution]{

  def getEntity(userId: String, institutionId: String): Future[Option[UserInstitution]]

  def getEntitiesForUser(userId: String): Future[Seq[UserInstitution]]

}

object UserInstitutionRepository{
  def roach: UserInstitutionRepository = new UserInstitutionRepositoryImpl()
}
