package services.application.Impl.cockroachdb

import domain.application.ApplicationResult
import repository.application.ApplicationResultRepository
import services.application.ApplicationResultService

import scala.concurrent.Future

class ApplicationResultServiceImpl extends ApplicationResultService{


  override def saveEntity(entity: ApplicationResult): Future[Option[ApplicationResult]] =
    ApplicationResultRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[ApplicationResult]] = {
    ApplicationResultRepository.roach.getEntities
  }

  override def getEntity(applicationResultId: String): Future[Option[ApplicationResult]] = {
    ApplicationResultRepository.roach.getEntity(applicationResultId)
  }

  override def deleteEntity(entity: ApplicationResult): Future[Boolean] = {
    ApplicationResultRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ApplicationResultRepository.roach.createTable
  }
}
