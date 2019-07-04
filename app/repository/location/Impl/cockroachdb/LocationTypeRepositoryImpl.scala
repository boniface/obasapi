package repository.location.Impl.cockroachdb

import domain.location.LocationType
import repository.location.LocationTypeRepository

import scala.concurrent.Future

class LocationTypeRepositoryImpl  extends LocationTypeRepository{

  override def saveEntity(entity: LocationType): Future[Boolean] = {
    LocationTypeRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[LocationType]] = {
    LocationTypeRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[LocationType]] = {
    LocationTypeRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: LocationType): Future[Boolean] = {
    LocationTypeRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    LocationTypeRepository.roach.createTable
  }
}
