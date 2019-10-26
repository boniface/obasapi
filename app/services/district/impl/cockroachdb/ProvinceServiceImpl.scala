package services.district.impl.cockroachdb

import domain.district.Province
import repository.district.ProvinceRepository
import services.district.ProvinceService

import scala.concurrent.Future

class ProvinceServiceImpl extends ProvinceService{



  override def saveEntity(entity: Province): Future[Option[Province]] =
    ProvinceRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[Province]] = {
    ProvinceRepository.roach.getEntities
  }

  override def getEntity(applicationStatusId: String): Future[Option[Province]] = {
    ProvinceRepository.roach.getEntity(applicationStatusId)
  }

  override def deleteEntity(entity: Province): Future[Boolean] = {
    ProvinceRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ProvinceRepository.roach.createTable
  }
}