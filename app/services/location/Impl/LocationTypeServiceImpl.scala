package services.location.Impl

import domain.location.LocationType
import services.location.LocationTypeService

import scala.concurrent.Future

class LocationTypeServiceImpl extends LocationTypeService {

  override def saveEntity(entity: LocationType): Future[Boolean] = ???

  override def getEntities: Future[Seq[LocationType]] = ???

  override def getEntity(id: String): Future[Option[LocationType]] = ???

  override def deleteEntity(entity: LocationType): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
