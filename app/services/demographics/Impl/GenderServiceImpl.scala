package services.demographics.Impl

import domain.demographics.Gender
import services.CrudService

import scala.concurrent.Future

class GenderServiceImpl extends CrudService[Gender]{


  override def saveEntity(entity: Gender): Future[Boolean] = ???

  override def getEntities: Future[Seq[Gender]] = ???

  override def getEntity(id: String): Future[Option[Gender]] = ???

  override def deleteEntity(entity: Gender): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
