package repository.application.impl.cockcroachdb

import domain.application.ApplicantType
import repository.application.ApplicantTypeRepository
import repository.application.impl.cockcroachdb.tables.ApplicantTypeTable

import scala.concurrent.Future

class ApplicantTypeRepositoryImpl extends ApplicantTypeRepository{

  override def saveEntity(entity: ApplicantType): Future[Boolean] = {
    Future.successful(ApplicantTypeTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[ApplicantType]] = {
    ApplicantTypeTable.getEntities
  }

  override def getEntity(applicantTypeId: String): Future[Option[ApplicantType]] = {
    ApplicantTypeTable.getEntity(applicantTypeId)
  }

  override def deleteEntity(entity: ApplicantType): Future[Boolean] = {
    Future.successful(ApplicantTypeTable.deleteEntity(entity.applicantTypeId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(ApplicantTypeTable.createTable)
  }
}
