package repository.demographics.impl.cockcroachdb

import domain.demographics.Town
import repository.demographics.TownRepository
import repository.demographics.impl.cockcroachdb.tables.TownTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Deprecated
class TownRepositoryImpl extends TownRepository{
  override def saveEntity(entity: Town): Future[Option[Town]] = {
    TownTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Town]] = {
    TownTable.getEntities
  }

  override def getEntity(townCode: String): Future[Option[Town]] = {
    TownTable.getEntity(townCode)
  }

  override def deleteEntity(entity: Town): Future[Boolean] = {
    TownTable.deleteEntity(entity.townCode)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(TownTable.createTable)
  }
}
