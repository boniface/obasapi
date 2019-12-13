package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionLocation
import repository.institutions.InstitutionLocationRepository
import services.institutions.InstitutionLocationService

import scala.concurrent.Future

class InstitutionLocationServiceImpl extends InstitutionLocationService {
  override def saveEntity(entity: InstitutionLocation): Future[Option[InstitutionLocation]] =
    InstitutionLocationRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionLocation]] =
    InstitutionLocationRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[InstitutionLocation]] =
    InstitutionLocationRepository.apply.getEntity(id)

  override def deleteEntity(entity: InstitutionLocation): Future[Boolean] =
    InstitutionLocationRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    InstitutionLocationRepository.apply.createTable

  override def getEntitiesForLocation(locationId: String): Future[Seq[InstitutionLocation]] =
    InstitutionLocationRepository.apply.getEntitiesForLocation(locationId)
}
