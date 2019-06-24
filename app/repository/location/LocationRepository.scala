package repository.location

import repository.Repository
import domain.location.Location
import repository.location.Impl.cassandra.LocationRepositoryImpl

trait LocationRepository extends Repository[Location] {

}

object LocationRepository{
  def apply: LocationRepository = new LocationRepositoryImpl()
}
