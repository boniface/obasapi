package services.location

import domain.location.Location
import services.CrudService
import services.location.Impl.cockroachdb.LocationServiceImpl

import scala.concurrent.Future

trait LocationService extends CrudService[Location]{
  def getParentEntities: Future[Seq[Location]]
  def getEntitiesForParent(locationParentId: String): Future[Seq[Location]]
}

object LocationService{
  def roach: LocationService = new LocationServiceImpl()
}
