package services.application.Impl

import domain.application.ApplicationStatus
import services.application.ApplicationStatusService

import scala.concurrent.Future

class ApplicationStatusServiceImpl extends ApplicationStatusService{



  override def saveEntity(entity: ApplicationStatus): Future[Boolean] = ???

  override def getEntities: Future[Seq[ApplicationStatus]] = ???

  override def getEntity(applicationStatusId: String): Future[Option[ApplicationStatus]] = ???

  override def deleteEntity(entity: ApplicationStatus): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
