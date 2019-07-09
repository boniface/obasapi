package services.location.Impl.cockroachdb

import domain.location.LocationType
import repository.location.LocationTypeRepository
import services.location.LocationTypeService

import scala.concurrent.Future

class LocationTypeServiceImpl extends LocationTypeService {

  override def saveEntity(entity: LocationType): Future[Boolean] =
    LocationTypeRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[LocationType]] =
    LocationTypeRepository.roach.getEntities

  override def getEntity(locationTypeId: String): Future[Option[LocationType]] =
    LocationTypeRepository.roach.getEntity(locationTypeId)

  override def deleteEntity(entity: LocationType): Future[Boolean] =
    LocationTypeRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    LocationTypeRepository.roach.createTable
}

