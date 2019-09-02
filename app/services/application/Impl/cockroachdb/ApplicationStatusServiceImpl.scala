package services.application.Impl.cockroachdb

import domain.application.ApplicationStatus
import repository.application.ApplicationStatusRepository
import services.application.ApplicationStatusService

import scala.concurrent.Future

class ApplicationStatusServiceImpl extends ApplicationStatusService{



  override def saveEntity(entity: ApplicationStatus): Future[Option[ApplicationStatus]] =
    ApplicationStatusRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[ApplicationStatus]] = {
    ApplicationStatusRepository.roach.getEntities
  }

  override def getEntity(applicationStatusId: String): Future[Option[ApplicationStatus]] = {
    ApplicationStatusRepository.roach.getEntity(applicationStatusId)
  }

  override def deleteEntity(entity: ApplicationStatus): Future[Boolean] = {
    ApplicationStatusRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ApplicationStatusRepository.roach.createTable
  }
}

