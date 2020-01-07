package repository.application

import domain.application.ApplicationStatus
import repository.Repository
import repository.application.impl.cockcroachdb.ApplicationStatusRepositoryImpl

import scala.concurrent.Future

trait ApplicationStatusRepository extends Repository[ApplicationStatus]{
  def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[ApplicationStatus]]
  def getEntitiesForApplication(applicationId: String): Future[Seq[ApplicationStatus]]
  def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[ApplicationStatus]]
  def getLatestForApplication(applicationId: String): Future[Option[ApplicationStatus]]
}

object ApplicationStatusRepository {
  def roach: ApplicationStatusRepository = new ApplicationStatusRepositoryImpl()
}
