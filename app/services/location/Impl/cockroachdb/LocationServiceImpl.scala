package services.location.Impl.cockroachdb

import domain.location.Location
import repository.location.LocationRepository
import services.location.LocationService

import scala.concurrent.Future

class LocationServiceImpl extends LocationService {

  override def saveEntity(entity: Location): Future[Option[Location]] =
    LocationRepository.roach.saveEntity(entity)
  override def getEntities: Future[Seq[Location]] =
    LocationRepository.roach.getEntities

  override def getEntity(locationId: String): Future[Option[Location]] =
    LocationRepository.roach.getEntity(locationId)

  override def deleteEntity(entity: Location): Future[Boolean] =
    LocationRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    LocationRepository.roach.createTable

}
