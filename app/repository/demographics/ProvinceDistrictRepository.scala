package repository.demographics

import domain.demographics.ProvinceDistrict
import repository.Repository
import repository.demographics.impl.cockcroachdb.ProvinceDistrictRepositoryImpl

import scala.concurrent.Future

@Deprecated
trait ProvinceDistrictRepository extends Repository[ProvinceDistrict] {
  def getEntity(provinceCode: String, districtCode: String): Future[Option[ProvinceDistrict]]
  def getEntitiesForProvince(provinceCode: String): Future[Seq[ProvinceDistrict]]
}

@Deprecated
object ProvinceDistrictRepository {
  def apply: ProvinceDistrictRepository = new ProvinceDistrictRepositoryImpl()
}
