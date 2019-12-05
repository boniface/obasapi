package services.util.generic.impl.cockroach

import domain.util.generic.GenericStatus
import repository.util.generic.GenericStatusRepository
import services.util.generic.GenericStatusService

import scala.concurrent.Future

class GenericStatusServiceImpl extends GenericStatusService{

  override def saveEntity(entity: GenericStatus): Future[Option[GenericStatus]] =
    GenericStatusRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[GenericStatus]] = {
    GenericStatusRepository.roach.getEntities
  }

  override def getEntity(id: String): Future[Option[GenericStatus]] = {
    GenericStatusRepository.roach.getEntity(id)
  }

  override def deleteEntity(entity: GenericStatus): Future[Boolean] = {
    GenericStatusRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    GenericStatusRepository.roach.createTable
  }
}

