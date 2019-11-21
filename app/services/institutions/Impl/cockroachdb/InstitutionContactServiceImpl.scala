package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionContact
import repository.institutions.InstitutionContactRepository
import services.institutions.InstitutionContactService

import scala.concurrent.Future

class InstitutionContactServiceImpl extends InstitutionContactService {
  override def getEntity(id: String, contactTypeId: String): Future[Option[InstitutionContact]] =
    InstitutionContactRepository.apply.getEntity(id, contactTypeId)

  override def getEntitiesForInstitution(id: String): Future[Seq[InstitutionContact]] =
    InstitutionContactRepository.apply.getEntitiesForInstitution(id)

  override def saveEntity(entity: InstitutionContact): Future[Option[InstitutionContact]] =
    InstitutionContactRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionContact]] =
    InstitutionContactRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[InstitutionContact]] = ???

  override def deleteEntity(entity: InstitutionContact): Future[Boolean] =
    InstitutionContactRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    InstitutionContactRepository.apply.createTable
}
