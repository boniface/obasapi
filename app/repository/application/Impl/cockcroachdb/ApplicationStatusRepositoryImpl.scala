package repository.application.Impl.cockcroachdb

import domain.application.ApplicationStatus
import repository.application.ApplicationStatusRepository
import repository.application.Impl.cockcroachdb.tables.ApplicationStatusTable

import scala.concurrent.Future

class ApplicationStatusRepositoryImpl extends ApplicationStatusRepository{
  override def saveEntity(entity: ApplicationStatus): Future[Boolean] ={
    Future.successful(ApplicationStatusTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[ApplicationStatus]] = {
    ApplicationStatusTable.getEntities
  }

  override def getEntity(applicationStatusId: String): Future[Option[ApplicationStatus]] = {
    ApplicationStatusTable.getEntity(applicationStatusId)
  }

  override def deleteEntity(entity: ApplicationStatus): Future[Boolean] = {
    Future.successful(ApplicationStatusTable.deleteEntity(entity.applicationStatusId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ApplicationStatusTable.createTable)
  }
}
