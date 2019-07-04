package services.location.Impl.cockroachdb

import domain.location.LocationType
import services.location.LocationTypeService

import scala.concurrent.Future

class LocationTypeServiceImpl extends LocationTypeService {

  override def saveEntity(entity: LocationType): Future[Boolean] =
    LocationTypeService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[LocationType]] =
    LocationTypeService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[LocationType]] =
    LocationTypeService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: LocationType): Future[Boolean] =
    LocationTypeService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    LocationTypeService.roach.createTable
}
