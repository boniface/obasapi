package services.demographics.Impl

import domain.demographics.Title
import services.CrudService

import scala.concurrent.Future

class TitleServiceImpl extends CrudService[Title]{


  override def saveEntity(entity: Title): Future[Boolean] = ???

  override def getEntities: Future[Seq[Title]] = ???

  override def getEntity(id: String): Future[Option[Title]] = ???

  override def deleteEntity(entity: Title): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
