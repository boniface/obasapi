package services.demographics.Impl.cockroachdb

import domain.demographics.Title
import services.demographics.TitleService

import scala.concurrent.Future

class TitleServiceImpl extends TitleService
{


  override def saveEntity(entity: Title): Future[Boolean] = {
    TitleService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Title]] = {
    TitleService.roach.getEntities
  }

  override def getEntity(titleId: String): Future[Option[Title]] = {
    TitleService.roach.getEntity(titleId)
  }

  override def deleteEntity(entity: Title): Future[Boolean] ={
    TitleService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    TitleService.roach.createTable
  }
}
