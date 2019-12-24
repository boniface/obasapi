package repository.demographics

import domain.demographics.District
import repository.Repository
import repository.demographics.impl.cockcroachdb.DistrictRepositoryImpl

@Deprecated
trait DistrictRepository extends Repository[District]{

}

@Deprecated
object DistrictRepository {
  def roach: DistrictRepository = new DistrictRepositoryImpl()
}