package services.demographics.Impl.cockroachdb

import domain.demographics.Gender
import services.demographics.GenderService

import scala.concurrent.Future

class GenderServiceImpl extends GenderService{


  override def saveEntity(entity: Gender): Future[Boolean] = {
    GenderService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Gender]] = {
    GenderService.roach.getEntities
  }

  override def getEntity(genderId: String): Future[Option[Gender]] = {
    GenderService.roach.getEntity(genderId)
  }

  override def deleteEntity(entity: Gender): Future[Boolean] = {
    GenderService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    GenderService.roach.createTable
  }
}
