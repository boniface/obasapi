package repository.location

import repository.Repository
import domain.location.Location
import repository.location.Impl.cockroachdb

trait LocationRepository extends Repository[Location] {

}

object LocationRepository{
  def roach: LocationRepository = new cockroachdb.LocationRepositoryImpl()
}
