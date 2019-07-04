package repository.demographics.Impl.cockcroachdb

import domain.demographics.Race
import repository.demographics.RaceRepository

import scala.concurrent.Future

class RaceRepositoryImpl extends RaceRepository {
  override def saveEntity(entity: Race): Future[Boolean] = {
   RaceRepository.roach.saveEntity(entity)
  }

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
