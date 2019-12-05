package repository.users

import domain.users.UserApplicationStatus
import repository.Repository
import repository.users.impl.cockroachdb.UserApplicationStatusRepositoryImpl

import scala.concurrent.Future

trait UserApplicationStatusRepository extends Repository[UserApplicationStatus]{
  def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[UserApplicationStatus]]
  def getEntitiesForApplication(applicationId: String): Future[Seq[UserApplicationStatus]]
  def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[UserApplicationStatus]]
  def getLatestForApplication(applicationId: String): Future[Option[UserApplicationStatus]]
}

object UserApplicationStatusRepository {
  def roach: UserApplicationStatusRepository = new UserApplicationStatusRepositoryImpl()
}
