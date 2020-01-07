package repository.institutions.impl.cockroachdb

import domain.institutions.InstitutionLocation
import repository.institutions.InstitutionLocationRepository
import repository.institutions.impl.cockroachdb.tables.InstitutionLocationTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionLocationRepositoryImpl extends InstitutionLocationRepository {
  override def saveEntity(entity: InstitutionLocation): Future[Option[InstitutionLocation]] =
    InstitutionLocationTable.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionLocation]] =
    InstitutionLocationTable.getEntities

  override def getEntity(id: String): Future[Option[InstitutionLocation]] =
    InstitutionLocationTable.getEntity(id)

  override def deleteEntity(entity: InstitutionLocation): Future[Boolean] =
    InstitutionLocationTable.deleteEntity(entity.institutionId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(InstitutionLocationTable.createTable)

  override def getEntitiesForLocation(locationId: String): Future[Seq[InstitutionLocation]] =
    InstitutionLocationTable.getEntitiesInLocation(locationId)
}
