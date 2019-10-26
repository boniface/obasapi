package services.demographics

import domain.demographics.District
import services.CrudService
import services.demographics.Impl.cockroachdb.DistrictServiceImpl

trait DistrictService extends CrudService[District]{
}

object DistrictService{

  def roach: DistrictService = new DistrictServiceImpl()
}
