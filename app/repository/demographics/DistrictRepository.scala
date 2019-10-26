package repository.demographics

import domain.demographics.District
import repository.Repository
import repository.demographics.impl.cockcroachdb.DistrictRepositoryImpl

trait DistrictRepository extends Repository[District]{

}
object DistrictRepository {
  def roach: DistrictRepository = new DistrictRepositoryImpl()
}