package repository.location.impl.cockroachdb

import domain.location.Location
import repository.location.LocationRepository
import repository.location.impl.cockroachdb.tables.LocationTable

import scala.concurrent.Future

class LocationRepositoryImpl  extends LocationRepository{

  override def saveEntity(entity: Location): Future[Boolean] = {
    Future.successful(LocationTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[Location]] = {
    LocationTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[Location]] = {
    LocationTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: Location): Future[Boolean] = {
    Future.successful(LocationTable.deleteEntity(entity.locationId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(LocationTable.createTable)
  }
}
