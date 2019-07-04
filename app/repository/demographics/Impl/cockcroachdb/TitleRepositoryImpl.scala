package repository.demographics.Impl.cockcroachdb

import domain.demographics.Title
import repository.demographics.Impl.cockcroachdb.tables.TitleTable
import repository.demographics.TitleRepository

import scala.concurrent.Future
/////////

class TitleRepositoryImpl extends TitleRepository {
  override def saveEntity(entity: Title): Future[Boolean] = {
    TitleRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Title]] = {
    TitleRepository.roach.getEntities
  }

  override def getEntity(titleId: String): Future[Option[Title]] = {
    TitleRepository.roach.getEntity(titleId)
  }

  override def deleteEntity(entity: Title): Future[Boolean] = {
    TitleRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
   TitleRepository.roach.createTable
  }
}
