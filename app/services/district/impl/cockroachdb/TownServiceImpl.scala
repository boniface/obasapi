package services.district.impl.cockroachdb

import domain.district.Town
import repository.district.TownRepository
import services.district.TownService

import scala.concurrent.Future

class TownServiceImpl extends TownService{



  override def saveEntity(entity: Town): Future[Option[Town]] =
    TownRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[Town]] = {
    TownRepository.roach.getEntities
  }

  override def getEntity(applicationStatusId: String): Future[Option[Town]] = {
    TownRepository.roach.getEntity(applicationStatusId)
  }

  override def deleteEntity(entity: Town): Future[Boolean] = {
    TownRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    TownRepository.roach.createTable
  }
}
