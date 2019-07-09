package repository.location.impl.cockroachdb

import domain.location.LocationType
import repository.location.LocationTypeRepository
import repository.location.impl.cockroachdb.tables.LocationTypeTable

import scala.concurrent.Future

class LocationTypeRepositoryImpl  extends LocationTypeRepository{

  override def saveEntity(entity: LocationType): Future[Boolean] = {
    Future.successful(LocationTypeTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[LocationType]] = {
    LocationTypeTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[LocationType]] = {
    LocationTypeTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: LocationType): Future[Boolean] = {
    Future.successful(LocationTypeTable.deleteEntity(entity.locationTypeId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(LocationTypeTable.createTable)
  }
}
