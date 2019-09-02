package services.demographics.Impl.cockroachdb

import domain.demographics.Race
import repository.demographics.RaceRepository
import services.demographics.RaceService

import scala.concurrent.Future

class RaceServiceImpl extends RaceService{

  override def saveEntity(entity: Race): Future[Option[Race]] =
    RaceRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[Race]] = {
    RaceRepository.roach.getEntities
  }

  override def getEntity(raceId: String): Future[Option[Race]] = {
    RaceRepository.roach.getEntity(raceId)
  }

  override def deleteEntity(entity: Race): Future[Boolean] = {
    RaceRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    RaceRepository.roach.createTable
  }
}
