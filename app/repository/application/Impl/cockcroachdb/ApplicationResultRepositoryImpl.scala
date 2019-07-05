package repository.application.Impl.cockcroachdb

import domain.application.ApplicationResult
import repository.application.ApplicationResultRepository
import repository.application.Impl.cockcroachdb.tables.ApplicationResultTable

import scala.concurrent.Future

class ApplicationResultRepositoryImpl extends ApplicationResultRepository{
  override def saveEntity(entity: ApplicationResult): Future[Boolean] = {
    ApplicationResultRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ApplicationResult]] = {
    ApplicationResultRepository.roach.getEntities
  }

  override def getEntity(applicationResultId: String): Future[Option[ApplicationResult]] = {
    ApplicationResultRepository.roach.getEntity(applicationResultId)
  }

  override def deleteEntity(entity: ApplicationResult): Future[Boolean] = {
    ApplicationResultRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ApplicationResultRepository.roach.createTable
  }
}
