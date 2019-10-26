package services.district

import domain.district.Town
import services.CrudService
import services.district.impl.cockroachdb.TownServiceImpl

trait TownService extends CrudService[Town]{
}

object TownService{

  def roach: TownService = new TownServiceImpl()
}
