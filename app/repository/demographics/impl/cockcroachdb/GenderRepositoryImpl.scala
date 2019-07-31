package repository.demographics.impl.cockcroachdb

import domain.demographics.Gender
import repository.demographics.GenderRepository
import repository.demographics.impl.cockcroachdb.tables.GenderTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class GenderRepositoryImpl extends GenderRepository{
  override def saveEntity(entity: Gender): Future[Option[Gender]] = {
    GenderTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Gender]] = {
    GenderTable.getEntities
  }

  override def getEntity(genderId: String): Future[Option[Gender]] = {
    GenderTable.getEntity(genderId)
  }

  override def deleteEntity(entity: Gender): Future[Boolean] = {
    GenderTable.deleteEntity(entity.genderId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(GenderTable.createTable)
  }
}

