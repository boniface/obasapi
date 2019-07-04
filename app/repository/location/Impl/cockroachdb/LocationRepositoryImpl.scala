package repository.location.Impl.cockroachdb

import domain.location.Location
import repository.location.LocationRepository

import scala.concurrent.Future

class LocationRepositoryImpl  extends LocationRepository{

  override def saveEntity(entity: Location): Future[Boolean] = {
    LocationRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Location]] = {
    LocationRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[Location]] = {
    LocationRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: Location): Future[Boolean] = {
    LocationRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    LocationRepository.roach.createTable
  }
}
