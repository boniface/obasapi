package repository.demographics.Impl.cockcroachdb

import domain.demographics.Gender
import repository.demographics.GenderRepository
import repository.demographics.Impl.cockcroachdb.tables.GenderTable

import scala.concurrent.Future

class GenderRepositoryImpl extends GenderRepository{
  override def saveEntity(entity: Gender): Future[Boolean] = {
    Future.successful(GenderTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[Gender]] ={
    GenderTable.getEntities
  }

  override def getEntity(genderId: String): Future[Option[Gender]] = {
    GenderTable.getEntity(genderId)
  }

  override def deleteEntity(entity: Gender): Future[Boolean] = {
    Future.successful(GenderTable.deleteEntity(entity.genderId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(GenderTable.createTable)
  }
}
