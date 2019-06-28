package repository.demographics.Impl.cockcroachdb

import domain.demographics.Title
import repository.demographics.Impl.cockcroachdb.tables.TitleTable
import repository.demographics.TitleRepository

import scala.concurrent.Future
/////////

class TitleRepositoryImpl extends TitleRepository {
  override def saveEntity(entity: Title): Future[Boolean] = {
    Future.successful(TitleTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[Title]] = {
    TitleTable.getEntities
  }

  override def getEntity(id: String): Future[Option[Title]] = {
    TitleTable.getEntity(id)
  }

  override def deleteEntity(entity: Title): Future[Boolean] = {
    Future.successful(TitleTable.deleteEntity(entity.id).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(TitleTable.createTable)
  }
}
