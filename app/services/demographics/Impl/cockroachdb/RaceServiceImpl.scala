package services.demographics.Impl.cockroachdb

import domain.demographics.Race
import services.demographics.RaceService

import scala.concurrent.Future

class RaceServiceImpl extends RaceService{

  override def saveEntity(entity: Race): Future[Boolean] = {
    RaceService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Race]] = {
    RaceService.roach.getEntities
  }

  override def getEntity(raceId: String): Future[Option[Race]] = {
    RaceService.roach.getEntity(raceId)
  }

  override def deleteEntity(entity: Race): Future[Boolean] = {
    RaceService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    RaceService.roach.createTable
  }
}
