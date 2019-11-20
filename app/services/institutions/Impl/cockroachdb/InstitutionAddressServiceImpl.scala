package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionAddress
import repository.institutions.InstitutionAddressRepository
import services.institutions.InstitutionAddressService

import scala.concurrent.Future

class InstitutionAddressServiceImpl extends InstitutionAddressService {
  override def getEntity(id: String, addressTypeId: String): Future[Option[InstitutionAddress]] =
    InstitutionAddressRepository.apply.getEntity(id, addressTypeId)

  override def getEntitiesForInstitution(id: String): Future[Seq[InstitutionAddress]] =
    InstitutionAddressRepository.apply.getEntitiesForInstitution(id)

  override def saveEntity(entity: InstitutionAddress): Future[Option[InstitutionAddress]] =
    InstitutionAddressRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionAddress]] =
    InstitutionAddressRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[InstitutionAddress]] = ???

  override def deleteEntity(entity: InstitutionAddress): Future[Boolean] =
    InstitutionAddressRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = InstitutionAddressRepository.apply.createTable
}
