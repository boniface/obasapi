package services.application.Impl

import domain.application.ApplicationResult
import services.CrudService

import scala.concurrent.Future

class ApplicationResultServiceImpl extends CrudService[ApplicationResult]{


  override def saveEntity(entity: ApplicationResult): Future[Boolean] = ???

  override def getEntities: Future[Seq[ApplicationResult]] = ???

  override def getEntity(applicationResultId: String): Future[Option[ApplicationResult]] = ???

  override def deleteEntity(entity: ApplicationResult): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
