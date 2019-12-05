package services.application.Impl.cockroachdb

import domain.application.ApplicationStatus
import repository.application.ApplicationStatusRepository
import services.application.ApplicationStatusService

import scala.concurrent.Future

class ApplicationStatusServiceImpl extends ApplicationStatusService {

  override def getEntitiesForApplication(applicationId: String): Future[Seq[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getEntitiesForApplication(applicationId)

  override def saveEntity(entity: ApplicationStatus): Future[Option[ApplicationStatus]] =
    ApplicationStatusRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getEntities

  override def getEntity(id: String): Future[Option[ApplicationStatus]] = ???

  override def deleteEntity(entity: ApplicationStatus): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ApplicationStatusRepository.roach.createTable

  override def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getEntitiesForAppnStatus(applicationId, statusId)

  override def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getLatestForAppnStatus(applicationId, statusId)

  override def getLatestForApplication(applicationId: String): Future[Option[ApplicationStatus]] =
    ApplicationStatusRepository.roach.getLatestForApplication(applicationId)
}
