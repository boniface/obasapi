package services.application.Impl

import domain.application.ApplicantType
import services.CrudService

import scala.concurrent.Future

class ApplicantTypeServiceImpl extends CrudService[ApplicantType]{

  override def saveEntity(entity: ApplicantType): Future[Boolean] = ???

  override def getEntities: Future[Seq[ApplicantType]] = ???

  override def getEntity(applicantTypeId: String): Future[Option[ApplicantType]] = ???

  override def deleteEntity(entity: ApplicantType): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
