package repository.demographics.impl.cockcroachdb

import domain.demographics.Title
import repository.demographics.impl.cockcroachdb.tables.TitleTable
import repository.demographics.TitleRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class TitleRepositoryImpl extends TitleRepository {
  override def saveEntity(entity: Title): Future[Boolean] = {
    TitleTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[Title]] = {
    TitleTable.getEntities
  }

  override def getEntity(titleId: String): Future[Option[Title]] = {
    TitleTable.getEntity(titleId)
  }

  override def deleteEntity(entity: Title): Future[Boolean] = {
    TitleTable.deleteEntity(entity.titleId)map(value=> value.isValidInt)

  }

  override def createTable: Future[Boolean] = {
    Future.successful(TitleTable.createTable)
  }
}
