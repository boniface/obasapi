package repository.demographics

import domain.demographics.Province
import repository.Repository
import repository.demographics.impl.cockcroachdb.ProvinceRepositoryImpl

@Deprecated
trait ProvinceRepository extends Repository[Province]{

}

@Deprecated
object ProvinceRepository{
  def roach: ProvinceRepository = new ProvinceRepositoryImpl()
}
