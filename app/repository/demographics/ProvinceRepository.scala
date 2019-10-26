package repository.demographics

import domain.demographics.Province
import repository.Repository
import repository.demographics.impl.cockcroachdb.ProvinceRepositoryImpl

trait ProvinceRepository extends Repository[Province]{

}
object ProvinceRepository{
  def roach: ProvinceRepository = new ProvinceRepositoryImpl()
}
