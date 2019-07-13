package repository.application.impl.cockcroachdb

import domain.application.ApplicationStatus
import repository.application.ApplicationStatusRepository
import repository.application.impl.cockcroachdb.tables.ApplicationStatusTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ApplicationStatusRepositoryImpl extends ApplicationStatusRepository{
  override def saveEntity(entity: ApplicationStatus): Future[Boolean] ={
    ApplicationStatusTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[ApplicationStatus]] = {
    ApplicationStatusTable.getEntities
  }

  override def getEntity(applicationStatusId: String): Future[Option[ApplicationStatus]] = {
    ApplicationStatusTable.getEntity(applicationStatusId)
  }

  override def deleteEntity(entity: ApplicationStatus): Future[Boolean] = {
    ApplicationStatusTable.deleteEntity(entity.applicationStatusId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ApplicationStatusTable.createTable)
  }
}

