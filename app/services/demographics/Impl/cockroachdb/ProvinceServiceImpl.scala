package services.demographics.Impl.cockroachdb

import domain.demographics.Province
import repository.demographics.ProvinceRepository
import services.demographics.ProvinceService

import scala.concurrent.Future

@Deprecated
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