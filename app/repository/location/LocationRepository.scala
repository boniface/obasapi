package repository.location

import repository.Repository
import domain.location.Location
import repository.location.impl.cockroachdb.LocationRepositoryImpl

import scala.concurrent.Future

trait LocationRepository extends Repository[Location] {
  def getParentEntities: Future[Seq[Location]]
  def getEntitiesForParent(locationParentId: String): Future[Seq[Location]]
}

object LocationRepository{
  def roach: LocationRepository = new LocationRepositoryImpl()
}
