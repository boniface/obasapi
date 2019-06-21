package services.location

import domain.location.LocationType
import services.CrudService
import services.location.Impl.LocationTypeServiceImpl

trait LocationTypeService extends CrudService[LocationType]{

}

object LocationTypeService{
  def apply: LocationTypeService = new LocationTypeServiceImpl()
}
