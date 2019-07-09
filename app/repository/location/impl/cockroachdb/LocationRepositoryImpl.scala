package repository.location.impl.cockroachdb

import domain.location.Location
import repository.location.LocationRepository
import repository.location.impl.cockroachdb.tables.LocationTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class LocationRepositoryImpl  extends LocationRepository{

  override def saveEntity(entity: Location): Future[Boolean] = {
    LocationTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[Location]] = {
    LocationTable.getEntities
  }

  override def getEntity(locationId: String): Future[Option[Location]] = {
    LocationTable.getEntity(locationId)
  }

  override def deleteEntity(entity: Location): Future[Boolean] = {
    LocationTable.deleteEntity(entity.locationId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(LocationTable.createTable)
  }
}


