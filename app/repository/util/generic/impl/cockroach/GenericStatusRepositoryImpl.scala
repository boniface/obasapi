package repository.util.generic.impl.cockroach

import domain.util.generic.GenericStatus
import repository.util.generic.GenericStatusRepository
import repository.util.generic.impl.cockroach.tables.GenericStatusTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class GenericStatusRepositoryImpl extends GenericStatusRepository{
  override def saveEntity(entity: GenericStatus): Future[Option[GenericStatus]] = {
    GenericStatusTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[GenericStatus]] = {
    GenericStatusTable.getEntities
  }

  override def getEntity(applicationStatusId: String): Future[Option[GenericStatus]] = {
    GenericStatusTable.getEntity(applicationStatusId)
  }

  override def deleteEntity(entity: GenericStatus): Future[Boolean] = {
    GenericStatusTable.deleteEntity(entity.id)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(GenericStatusTable.createTable)
  }
}

