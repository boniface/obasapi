package services.demographics

import domain.demographics.Town
import services.CrudService
import services.demographics.Impl.cockroachdb.TownServiceImpl

@Deprecated
trait TownService extends CrudService[Town]{
}

@Deprecated
object TownService{
  def roach: TownService = new TownServiceImpl()
}
