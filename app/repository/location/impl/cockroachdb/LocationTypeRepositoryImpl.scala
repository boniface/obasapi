package repository.location.impl.cockroachdb

import domain.location.LocationType
import repository.location.LocationTypeRepository
import repository.location.impl.cockroachdb.tables.LocationTypeTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class LocationTypeRepositoryImpl  extends LocationTypeRepository{

  override def saveEntity(entity: LocationType): Future[Option[LocationType]] = {
    LocationTypeTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[LocationType]] = {
    LocationTypeTable.getEntities
  }

  override def getEntity(locationTypeId: String): Future[Option[LocationType]] = {
    LocationTypeTable.getEntity(locationTypeId)
  }

  override def deleteEntity(entity: LocationType): Future[Boolean] = {
    LocationTypeTable.deleteEntity(entity.locationTypeId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(LocationTypeTable.createTable)
  }
}
