package services.location.Impl

import domain.location.Location
import services.location.LocationService

import scala.concurrent.Future

class LocationServiceImpl extends LocationService {

  override def saveEntity(entity: Location): Future[Boolean] = ???

  override def getEntities: Future[Seq[Location]] = ???

  override def getEntity(id: String): Future[Option[Location]] = ???

  override def deleteEntity(entity: Location): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}
