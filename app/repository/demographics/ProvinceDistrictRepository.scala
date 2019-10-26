package repository.demographics

import domain.demographics.ProvinceDistrict
import repository.Repository
import repository.demographics.impl.cockcroachdb.ProvinceDistrictRepositoryImpl

import scala.concurrent.Future

trait ProvinceDistrictRepository extends Repository[ProvinceDistrict] {
  def getEntity(provinceCode: String, districtCode: String): Future[Option[ProvinceDistrict]]
  def getEntitiesForProvince(provinceCode: String): Future[Seq[ProvinceDistrict]]
}

object ProvinceDistrictRepository {
  def apply: ProvinceDistrictRepository = new ProvinceDistrictRepositoryImpl()
}
