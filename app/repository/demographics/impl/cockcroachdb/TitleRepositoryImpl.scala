package repository.demographics.impl.cockcroachdb

import domain.demographics.Title
import repository.demographics.TitleRepository
import repository.demographics.impl.cockcroachdb.tables.TitleTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TitleRepositoryImpl extends TitleRepository {
  override def saveEntity(entity: Title): Future[Option[Title]] = {
    TitleTable.saveEntity(entity)
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
