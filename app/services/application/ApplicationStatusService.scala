package services.application

import domain.application.ApplicationStatus
import services.CrudService
import services.application.Impl.cockroachdb.ApplicationStatusServiceImpl

import scala.concurrent.Future

trait ApplicationStatusService extends CrudService[ApplicationStatus] {
  def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[ApplicationStatus]]
  def getEntitiesForApplication(applicationId: String): Future[Seq[ApplicationStatus]]
  def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[ApplicationStatus]]
  def getLatestForApplication(applicationId: String): Future[Option[ApplicationStatus]]
}

object ApplicationStatusService{
  def roach: ApplicationStatusService = new ApplicationStatusServiceImpl()
}
