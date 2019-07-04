package services.demographics.Impl

import domain.demographics.Gender
import services.demographics.GenderService

import scala.concurrent.Future

class GenderServiceImpl extends GenderService{


  override def saveEntity(entity: Gender): Future[Boolean] = ???

  override def getEntities: Future[Seq[Gender]] = ???

  override def getEntity(genderId: String): Future[Option[Gender]] = ???

  override def deleteEntity(entity: Gender): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
