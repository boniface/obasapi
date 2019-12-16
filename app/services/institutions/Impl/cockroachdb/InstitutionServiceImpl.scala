package services.institutions.Impl.cockroachdb

import domain.institutions.Institution
import repository.institutions.InstitutionRepository
import services.institutions.InstitutionService

import scala.concurrent.Future

class InstitutionServiceImpl extends InstitutionService {
  override def saveEntity(entity: Institution): Future[Option[Institution]] =
    InstitutionRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[Institution]] = InstitutionRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[Institution]] =
    InstitutionRepository.apply.getEntity(id)

  override def deleteEntity(entity: Institution): Future[Boolean] =
    InstitutionRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = InstitutionRepository.apply.createTable

  override def getEntitiesForInstitutionTypeId(institutionTypeId: String): Future[Seq[Institution]] =
    InstitutionRepository.apply.getEntitiesForInstitutionTypeId(institutionTypeId)
}
