package services.district

import domain.district.District
import services.CrudService
import services.district.impl.cockroachdb.DistrictServiceImpl

trait DistrictService extends CrudService[District]{
}

object DistrictService{

  def roach: DistrictService = new DistrictServiceImpl()
}
