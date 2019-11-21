package repository.institutions.impl.cockroachdb

import domain.institutions.InstitutionType
import repository.institutions.InstitutionTypeRepository
import repository.institutions.impl.cockroachdb.tables.InstitutionTypeTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionTypeRepositoryImpl extends InstitutionTypeRepository {
  override def saveEntity(entity: InstitutionType): Future[Option[InstitutionType]] = InstitutionTypeTable.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionType]] = InstitutionTypeTable.getEntities

  override def getEntity(id: String): Future[Option[InstitutionType]] = InstitutionTypeTable.getEntity(id)

  override def deleteEntity(entity: InstitutionType): Future[Boolean] =
    InstitutionTypeTable.deleteEntity(entity.id).map(value => value.isValidInt)

  override def createTable: Future[Boolean] = Future.successful(InstitutionTypeTable.createTable)
}
