package services.location.Impl.cockroachdb

import domain.location.Location
import services.location.LocationService

import scala.concurrent.Future

class LocationServiceImpl extends LocationService {

  override def saveEntity(entity: Location): Future[Boolean] =
    LocationService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[Location]] =
    LocationService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[Location]] =
    LocationService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: Location): Future[Boolean] =
    LocationService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    LocationService.roach.createTable

}
