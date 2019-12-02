package services.demographics

import domain.demographics.Town
import services.CrudService
import services.demographics.Impl.cockroachdb.TownServiceImpl

trait TownService extends CrudService[Town]{
}

object TownService{

  def roach: TownService = new TownServiceImpl()
}
