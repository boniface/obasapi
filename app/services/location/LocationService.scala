package services.location

import domain.location.Location
import services.CrudService
import services.location.Impl.cockroachdb

trait LocationService extends CrudService[Location]{

}

object LocationService{
  def roach: LocationService = new cockroachdb.LocationServiceImpl()
}
