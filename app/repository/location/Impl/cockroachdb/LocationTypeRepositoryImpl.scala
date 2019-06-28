package repository.location.Impl.cockroachdb

import domain.location.LocationType
import repository.location.Impl.cockroachdb.tables.LocationTypeTable
import repository.location.LocationTypeRepository

import scala.concurrent.Future

class LocationTypeRepositoryImpl  extends LocationTypeRepository{

  override def saveEntity(entity: LocationType): Future[Boolean] = {
    Future.successful(LocationTypeTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[LocationType]] = {
    LocationTypeTable.getEntities
  }

  override def getEntity(locationTypeId: String): Future[Option[LocationType]] = {
    LocationTypeTable.getEntity(locationTypeId)
  }

  override def deleteEntity(entity: LocationType): Future[Boolean] = {
    Future.successful(LocationTypeTable.deleteEntity(entity.locationTypeId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(LocationTypeTable.createTable)
  }
}
