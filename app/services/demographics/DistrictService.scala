package services.demographics

import domain.demographics.District
import services.CrudService
import services.demographics.Impl.cockroachdb.DistrictServiceImpl

@Deprecated
trait DistrictService extends CrudService[District]{
}

@Deprecated
object DistrictService{

  def roach: DistrictService = new DistrictServiceImpl()
}
