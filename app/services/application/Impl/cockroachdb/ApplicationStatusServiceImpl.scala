package services.application.Impl.cockroachdb

import domain.application.ApplicationStatus
import services.application.ApplicationStatusService

import scala.concurrent.Future

class ApplicationStatusServiceImpl extends ApplicationStatusService{



  override def saveEntity(entity: ApplicationStatus): Future[Boolean] = {
    ApplicationStatusService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ApplicationStatus]] = {
    ApplicationStatusService.roach.getEntities
  }

  override def getEntity(applicationStatusId: String): Future[Option[ApplicationStatus]] = {
    ApplicationStatusService.roach.getEntity(applicationStatusId)
  }

  override def deleteEntity(entity: ApplicationStatus): Future[Boolean] = {
    ApplicationStatusService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ApplicationStatusService.roach.createTable
  }
}
