package services.demographics

import domain.demographics.DistrictTown
import services.CrudService
import services.demographics.Impl.cockroachdb.DistrictTownServiceImpl

import scala.concurrent.Future

@Deprecated
trait DistrictTownService extends CrudService[DistrictTown] {
  def getEntity(districtCode: String, townCode: String): Future[Option[DistrictTown]]
  def getEntitiesForDistrict(districtCode: String): Future[Seq[DistrictTown]]
}

@Deprecated
object DistrictTownService {
  def apply: DistrictTownService = new DistrictTownServiceImpl()
}
