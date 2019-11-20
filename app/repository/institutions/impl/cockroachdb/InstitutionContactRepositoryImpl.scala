package repository.institutions.impl.cockroachdb

import domain.institutions.InstitutionContact
import repository.institutions.InstitutionContactRepository
import repository.institutions.impl.cockroachdb.tables.{InstitutionContactTable, InstitutionContactTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionContactRepositoryImpl extends InstitutionContactRepository {
  override def getEntity(id: String, addressTypeId: String): Future[Option[InstitutionContact]] =
    InstitutionContactTable.getEntity(id, addressTypeId)

  override def getEntitiesForInstitution(id: String): Future[Seq[InstitutionContact]] =
    InstitutionContactTable.getEntitiesForInstitution(id)

  override def saveEntity(entity: InstitutionContact): Future[Option[InstitutionContact]] =
    InstitutionContactTable.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionContact]] =
    InstitutionContactTable.getEntities

  override def getEntity(id: String): Future[Option[InstitutionContact]] = ???

  override def deleteEntity(entity: InstitutionContact): Future[Boolean] =
    InstitutionContactTable.deleteEntity(entity.institutionId, entity.contactTypeId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(InstitutionContactTableCreate.createTable)
}
