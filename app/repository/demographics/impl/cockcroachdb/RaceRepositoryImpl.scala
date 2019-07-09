package repository.demographics.impl.cockcroachdb

import domain.demographics.Race
import repository.demographics.impl.cockcroachdb.tables.RaceTable
import repository.demographics.RaceRepository

import scala.concurrent.Future

class RaceRepositoryImpl extends RaceRepository {
  override def saveEntity(entity: Race): Future[Boolean] = {
   Future.successful(RaceTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[Race]] = {
    RaceTable.getEntities
  }

  override def getEntity(raceId: String): Future[Option[Race]] = {
    RaceTable.getEntity(raceId)
  }

  override def deleteEntity(entity: Race): Future[Boolean] = {
    Future.successful(RaceTable.deleteEntity(entity.raceId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(RaceTable.createTable)
  }
}
