package services.demographics.Impl.cockroachdb

import domain.demographics.Title
import repository.demographics.TitleRepository
import services.demographics.TitleService

import scala.concurrent.Future

class TitleServiceImpl extends TitleService
{


  override def saveEntity(entity: Title): Future[Option[Title]] = {
    TitleRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Title]] = {
    TitleRepository.roach.getEntities
  }

  override def getEntity(titleId: String): Future[Option[Title]] = {
    TitleRepository.roach.getEntity(titleId)
  }

  override def deleteEntity(entity: Title): Future[Boolean] ={
    TitleRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    TitleRepository.roach.createTable
  }
}

