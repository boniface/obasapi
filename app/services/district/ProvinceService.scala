package services.district

import domain.district.Province
import services.CrudService
import services.district.impl.cockroachdb.ProvinceServiceImpl

trait ProvinceService extends CrudService[Province]{
}

object ProvinceService{

  def roach: ProvinceService = new ProvinceServiceImpl()
}