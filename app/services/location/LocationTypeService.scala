package services.location

import domain.location.LocationType
import services.CrudService
import services.location.Impl.cockroachdb.LocationTypeServiceImpl

trait LocationTypeService extends CrudService[LocationType]{

}

object LocationTypeService{
  def roach: LocationTypeService = new LocationTypeServiceImpl()
}
