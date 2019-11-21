package repository.institutions.impl.cockroachdb

import domain.institutions.InstitutionAddress
import repository.institutions.InstitutionAddressRepository
import repository.institutions.impl.cockroachdb.tables.{InstitutionAddressTable, InstitutionAddressTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionAddressRepositoryImpl extends InstitutionAddressRepository{
  override def saveEntity(entity: InstitutionAddress): Future[Option[InstitutionAddress]] =
    InstitutionAddressTable.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionAddress]] =
    InstitutionAddressTable.getEntities

  override def getEntity(id: String): Future[Option[InstitutionAddress]] = ???

  override def deleteEntity(entity: InstitutionAddress): Future[Boolean] =
    InstitutionAddressTable.deleteEntity(entity.institutionId, entity.addressTypeId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] = Future.successful(InstitutionAddressTableCreate.createTable)

  override def getEntity(id: String, addressTypeId: String): Future[Option[InstitutionAddress]] =
    InstitutionAddressTable.getEntity(id, addressTypeId)

  override def getEntitiesForInstitution(id: String): Future[Seq[InstitutionAddress]] =
    InstitutionAddressTable.getEntitiesForInstitution(id)
}
