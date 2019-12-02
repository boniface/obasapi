package repository.demographics

import domain.demographics.DistrictTown
import repository.Repository
import repository.demographics.impl.cockcroachdb.DistrictTownRepositoryImpl

import scala.concurrent.Future

trait DistrictTownRepository extends Repository[DistrictTown] {
  def getEntity(districtCode: String, townCode: String): Future[Option[DistrictTown]]
  def getEntitiesForDistrict(districtCode: String): Future[Seq[DistrictTown]]
}

object DistrictTownRepository {
  def apply: DistrictTownRepository = new DistrictTownRepositoryImpl()
}
