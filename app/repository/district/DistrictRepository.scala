package repository.district

import domain.district.District
import repository.Repository
import repository.district.impl.cockroachdb.DistrictRepositoryImpl

trait DistrictRepository extends Repository[District]{

}
object DistrictRepository {
  def roach: DistrictRepository = new DistrictRepositoryImpl()
}