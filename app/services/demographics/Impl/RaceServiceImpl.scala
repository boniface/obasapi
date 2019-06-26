package services.demographics.Impl

import domain.demographics.Race
import services.CrudService

import scala.concurrent.Future

class RaceServiceImpl extends CrudService[Race]{

  override def saveEntity(entity: Race): Future[Boolean] = ???

  override def getEntities: Future[Seq[Race]] = ???

  override def getEntity(raceId: String): Future[Option[Race]] = ???

  override def deleteEntity(entity: Race): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
