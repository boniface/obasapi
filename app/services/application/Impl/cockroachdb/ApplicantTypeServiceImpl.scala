package services.application.Impl.cockroachdb

import domain.application.ApplicantType
import services.application.ApplicantTypeService

import scala.concurrent.Future

class ApplicantTypeServiceImpl extends ApplicantTypeService{

  override def saveEntity(entity: ApplicantType): Future[Boolean] = {
    ApplicantTypeService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ApplicantType]] = {
    ApplicantTypeService.roach.getEntities
  }

  override def getEntity(applicantTypeId: String): Future[Option[ApplicantType]] = {
    ApplicantTypeService.roach.getEntity(applicantTypeId)
  }

  override def deleteEntity(entity: ApplicantType): Future[Boolean] = {
    ApplicantTypeService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ApplicantTypeService.roach.createTable
  }
}
