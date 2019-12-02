package services.demographics

import domain.demographics.Province
import services.CrudService
import services.demographics.Impl.cockroachdb.ProvinceServiceImpl

trait ProvinceService extends CrudService[Province]{
}

object ProvinceService{

  def roach: ProvinceService = new ProvinceServiceImpl()
}