package services.district.impl.cockroachdb

import domain.district.District
import repository.district.DistrictRepository
import services.district.DistrictService

import scala.concurrent.Future

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
