package repository.location

import domain.location.LocationType
import repository.Repository
import repository.location.Impl.cassandra.LocationTypeRepositoryImpl

trait LocationTypeRepository extends Repository[LocationType] {

}

object LocationTypeRepository{
  def apply: LocationTypeRepository = new LocationTypeRepositoryImpl()
}
