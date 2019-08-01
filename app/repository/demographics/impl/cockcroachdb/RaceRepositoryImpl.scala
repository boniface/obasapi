package repository.demographics.impl.cockcroachdb

import domain.demographics.Race
import repository.demographics.impl.cockcroachdb.tables.RaceTable
import repository.demographics.RaceRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class RaceRepositoryImpl extends RaceRepository {
  override def saveEntity(entity: Race): Future[Option[Race]] = {
    RaceTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Race]] = {
    RaceTable.getEntities
  }

  override def getEntity(raceId: String): Future[Option[Race]] = {
    RaceTable.getEntity(raceId)
  }

  override def deleteEntity(entity: Race): Future[Boolean] = {
    RaceTable.deleteEntity(entity.raceId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(RaceTable.createTable)
  }
}

