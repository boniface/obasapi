package repository.application.impl.cockcroachdb

import domain.application.ApplicationResult
import repository.application.ApplicationResultRepository
import repository.application.impl.cockcroachdb.tables.ApplicationResultTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ApplicationResultRepositoryImpl extends ApplicationResultRepository{
  override def saveEntity(entity: ApplicationResult): Future[Option[ApplicationResult]] = {
    ApplicationResultTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ApplicationResult]] = {
    ApplicationResultTable.getEntities
  }

  override def getEntity(applicationResultId: String): Future[Option[ApplicationResult]] = {
    ApplicationResultTable.getEntity(applicationResultId)
  }

  override def deleteEntity(entity: ApplicationResult): Future[Boolean] = {
    ApplicationResultTable.deleteEntity(entity.applicationResultId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ApplicationResultTable.createTable)
  }
}
