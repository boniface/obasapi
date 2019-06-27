package services.demographics.Impl

import domain.demographics.Race
import services.demographics.RaceService

import scala.concurrent.Future

class RaceServiceImpl extends RaceService{

  override def saveEntity(entity: Race): Future[Boolean] = ???

  override def getEntities: Future[Seq[Race]] = ???

  override def getEntity(raceId: String): Future[Option[Race]] = ???

  override def deleteEntity(entity: Race): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
