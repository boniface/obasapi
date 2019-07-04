package repository.application.Impl.cockcroachdb

import domain.application.ApplicantType
import repository.application.ApplicantTypeRepository
import repository.application.Impl.cockcroachdb.tables.ApplicantTypeTable

import scala.concurrent.Future

class ApplicantTypeRepositoryImpl extends ApplicantTypeRepository{

  override def saveEntity(entity: ApplicantType): Future[Boolean] = {
    ApplicantTypeRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[ApplicantType]] = {
    ApplicantTypeRepository.roach.getEntities
  }

  override def getEntity(applicantTypeId: String): Future[Option[ApplicantType]] = {
    ApplicantTypeRepository.roach.getEntity(applicantTypeId)
  }

  override def deleteEntity(entity: ApplicantType): Future[Boolean] = {
   ApplicantTypeRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    ApplicantTypeRepository.roach.createTable
  }
}
