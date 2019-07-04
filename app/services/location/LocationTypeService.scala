package services.location

import domain.location.LocationType
import services.CrudService
import services.location.Impl.cockroachdb

trait LocationTypeService extends CrudService[LocationType]{

}

object LocationTypeService{
  def roach: LocationTypeService = new cockroachdb.LocationTypeServiceImpl()
}
