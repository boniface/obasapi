package services.demographics

import domain.demographics.Province
import services.CrudService
import services.demographics.Impl.cockroachdb.ProvinceServiceImpl

@Deprecated
trait ProvinceService extends CrudService[Province]{
}

@Deprecated
object ProvinceService{

  def roach: ProvinceService = new ProvinceServiceImpl()
}