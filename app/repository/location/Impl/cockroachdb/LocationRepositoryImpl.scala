package repository.location.Impl.cockroachdb

import domain.location.Location
import repository.location.Impl.cockroachdb.tables.LocationTable
import repository.location.LocationRepository

import scala.concurrent.Future

class LocationRepositoryImpl  extends LocationRepository{

  override def saveEntity(entity: Location): Future[Boolean] = {
    Future.successful(LocationTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[Location]] = {
    LocationTable.getEntities
  }

  override def getEntity(locationId: String): Future[Option[Location]] = {
    LocationTable.getEntity(locationId)
  }

  override def deleteEntity(entity: Location): Future[Boolean] = {
    Future.successful(LocationTable.deleteEntity(entity.locationId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(LocationTable.createTable)
  }
}
