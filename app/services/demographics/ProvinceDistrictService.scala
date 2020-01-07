package services.demographics

import domain.demographics.ProvinceDistrict
import services.CrudService
import services.demographics.Impl.cockroachdb.ProvinceDistrictServiceImpl

import scala.concurrent.Future

@Deprecated
trait ProvinceDistrictService extends CrudService[ProvinceDistrict] {
  def getEntity(provinceCode: String, districtCode: String): Future[Option[ProvinceDistrict]]
  def getEntitiesForProvince(provinceCode: String): Future[Seq[ProvinceDistrict]]
}

@Deprecated
object ProvinceDistrictService {
  def apply: ProvinceDistrictService = new ProvinceDistrictServiceImpl()
}