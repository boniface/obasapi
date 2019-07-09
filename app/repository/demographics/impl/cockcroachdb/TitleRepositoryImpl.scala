package repository.demographics.impl.cockcroachdb

import domain.demographics.Title
import repository.demographics.impl.cockcroachdb.tables.TitleTable
import repository.demographics.TitleRepository

import scala.concurrent.Future

class TitleRepositoryImpl extends TitleRepository {
  override def saveEntity(entity: Title): Future[Boolean] = {
    Future.successful(TitleTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[Title]] = {
    TitleTable.getEntities
  }

  override def getEntity(titleId: String): Future[Option[Title]] = {
    TitleTable.getEntity(titleId)
  }

  override def deleteEntity(entity: Title): Future[Boolean] = {
    Future.successful(TitleTable.deleteEntity(entity.titleId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(TitleTable.createTable)
  }
}
