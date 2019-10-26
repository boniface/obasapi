package services.demographics.Impl.cockroachdb

import domain.demographics.DistrictTown
import repository.demographics.DistrictTownRepository
import services.demographics.DistrictTownService

import scala.concurrent.Future

class DistrictTownServiceImpl extends DistrictTownService {
  override def getEntity(districtCode: String, townCode: String): Future[Option[DistrictTown]] =
    DistrictTownRepository.apply.getEntity(districtCode, townCode)

  override def getEntitiesForDistrict(districtCode: String): Future[Seq[DistrictTown]] =
    DistrictTownRepository.apply.getEntitiesForDistrict(districtCode)

  override def saveEntity(entity: DistrictTown): Future[Option[DistrictTown]] =
    DistrictTownRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[DistrictTown]] = DistrictTownRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[DistrictTown]] = ???

  override def deleteEntity(entity: DistrictTown): Future[Boolean] = DistrictTownRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = DistrictTownRepository.apply.createTable
}
