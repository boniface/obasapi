package repository.location

import domain.location.LocationType
import repository.Repository
import repository.location.impl.cockroachdb.LocationTypeRepositoryImpl

trait LocationTypeRepository extends Repository[LocationType] {

}

object LocationTypeRepository{
  def roach: LocationTypeRepository = new LocationTypeRepositoryImpl()
}
