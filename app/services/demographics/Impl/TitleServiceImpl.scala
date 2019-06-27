package services.demographics.Impl

import domain.demographics.Title
import services.demographics.TitleService

import scala.concurrent.Future

class TitleServiceImpl extends TitleService
{


  override def saveEntity(entity: Title): Future[Boolean] = ???

  override def getEntities: Future[Seq[Title]] = ???

  override def getEntity(titleId: String): Future[Option[Title]] = ???

  override def deleteEntity(entity: Title): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
