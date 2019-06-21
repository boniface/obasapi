package services.location

import domain.location.Location
import services.CrudService
import services.location.Impl.LocationServiceImpl

trait LocationService extends CrudService[Location]{

}

object LocationService{
  def apply: LocationService = new LocationServiceImpl()
}
