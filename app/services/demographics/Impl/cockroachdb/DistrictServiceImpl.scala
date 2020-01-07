package services.demographics.Impl.cockroachdb

import domain.demographics.District
import repository.demographics.DistrictRepository
import services.demographics.DistrictService

import scala.concurrent.Future

@Deprecated
class DistrictServiceImpl extends DistrictService{

  override def saveEntity(entity: District): Future[Option[District]] =
    DistrictRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[District]] = {
    DistrictRepository.roach.getEntities
  }

  override def getEntity(applicationStatusId: String): Future[Option[District]] = {
    DistrictRepository.roach.getEntity(applicationStatusId)
  }

  override def deleteEntity(entity: District): Future[Boolean] = {
    DistrictRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    DistrictRepository.roach.createTable
  }
}
