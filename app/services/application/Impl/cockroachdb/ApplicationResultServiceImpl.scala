package services.application.Impl.cockroachdb

import domain.application.ApplicationResult
import services.application.ApplicationResultService

import scala.concurrent.Future

class ApplicationResultServiceImpl extends ApplicationResultService{


  override def saveEntity(entity: ApplicationResult): Future[Boolean] = {
    ApplicationResultService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ApplicationResult]] = {
    ApplicationResultService.roach.getEntities
  }

  override def getEntity(applicationResultId: String): Future[Option[ApplicationResult]] = {
    ApplicationResultService.roach.getEntity(applicationResultId)
  }

  override def deleteEntity(entity: ApplicationResult): Future[Boolean] = {
    ApplicationResultService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ApplicationResultService.roach.createTable
  }
}
