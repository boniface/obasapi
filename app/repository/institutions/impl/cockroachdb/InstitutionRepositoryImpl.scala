package repository.institutions.impl.cockroachdb

import domain.institutions.Institution
import repository.institutions.InstitutionRepository
import repository.institutions.impl.cockroachdb.tables.InstitutionTable

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionRepositoryImpl extends InstitutionRepository {
  override def saveEntity(entity: Institution): Future[Option[Institution]] =
    InstitutionTable.saveEntity(entity)

  override def getEntities: Future[Seq[Institution]] =
    InstitutionTable.getEntities

  override def getEntity(id: String): Future[Option[Institution]] =
    InstitutionTable.getEntity(id)

  override def deleteEntity(entity: Institution): Future[Boolean] =
    InstitutionTable.deleteEntity(entity.id).map(value => value.isValidInt)

  override def createTable: Future[Boolean] = Future.successful(InstitutionTable.createTable)
}
