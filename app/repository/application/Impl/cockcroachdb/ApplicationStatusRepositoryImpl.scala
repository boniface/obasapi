package repository.application.Impl.cockcroachdb

import domain.application.ApplicationStatus
import repository.application.ApplicationStatusRepository
import repository.application.Impl.cockcroachdb.tables.ApplicationStatusTable

import scala.concurrent.Future

class ApplicationStatusRepositoryImpl extends ApplicationStatusRepository{
  override def saveEntity(entity: ApplicationStatus): Future[Boolean] ={
    ApplicationStatusRepository.roach.saveEntity(entity)
  }

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
