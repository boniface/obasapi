package repository.location.impl.cockroachdb

import domain.location.LocationType
import repository.location.LocationTypeRepository
import repository.location.impl.cockroachdb.tables.LocationTypeTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class LocationTypeRepositoryImpl  extends LocationTypeRepository{

  override def saveEntity(entity: LocationType): Future[Boolean] = {
    LocationTypeTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[LocationType]] = {
    LocationTypeTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[LocationType]] = {
    LocationTypeTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: LocationType): Future[Boolean] = {
    LocationTypeTable.deleteEntity(entity.locationTypeId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(LocationTypeTable.createTable)
  }
}
