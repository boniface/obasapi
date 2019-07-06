package repository.application.impl.cockcroachdb

import domain.application.ApplicationResult
import repository.application.ApplicationResultRepository
import repository.application.impl.cockcroachdb.tables.ApplicationResultTable

import scala.concurrent.Future

class ApplicationResultRepositoryImpl extends ApplicationResultRepository{
  override def saveEntity(entity: ApplicationResult): Future[Boolean] = {
    Future.successful(ApplicationResultTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[ApplicationResult]] = {
    ApplicationResultTable.getEntities
  }

  override def getEntity(applicationResultId: String): Future[Option[ApplicationResult]] = {
    ApplicationResultTable.getEntity(applicationResultId)
  }

  override def deleteEntity(entity: ApplicationResult): Future[Boolean] = {
    Future.successful(ApplicationResultTable.deleteEntity(entity.applicationResultId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ApplicationResultTable.createTable)
  }
}
