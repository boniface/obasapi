package repository.district

import domain.district.Province
import repository.Repository
import repository.district.impl.cockroachdb.ProvinceRepositoryImpl

trait ProvinceRepository extends Repository[Province]{

}
object ProvinceRepository{
  def roach: ProvinceRepository = new ProvinceRepositoryImpl()
}
