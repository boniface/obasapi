package services.demographics.Impl.cockroachdb

import domain.demographics.Gender
import repository.demographics.GenderRepository
import services.demographics.GenderService

import scala.concurrent.Future

class GenderServiceImpl extends GenderService{

  override def saveEntity(entity: Gender): Future[Option[Gender]] =
    GenderRepository.roach.saveEntity(entity)
  override def getEntities: Future[Seq[Gender]] = {
    GenderRepository.roach.getEntities
  }

  override def getEntity(genderId: String): Future[Option[Gender]] = {
    GenderRepository.roach.getEntity(genderId)
  }

  override def deleteEntity(entity: Gender): Future[Boolean] = {
    GenderRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    GenderRepository.roach.createTable
  }
}
