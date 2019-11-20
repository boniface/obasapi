package services.institutions.Impl.cockroachdb

import domain.institutions.InstitutionType
import repository.institutions.InstitutionTypeRepository
import services.institutions.InstitutionTypeService

import scala.concurrent.Future

class InstitutionTypeServiceImpl extends InstitutionTypeService {
  override def saveEntity(entity: InstitutionType): Future[Option[InstitutionType]] =
    InstitutionTypeRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[InstitutionType]] =
    InstitutionTypeRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[InstitutionType]] =
    InstitutionTypeRepository.apply.getEntity(id)

  override def deleteEntity(entity: InstitutionType): Future[Boolean] =
    InstitutionTypeRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = InstitutionTypeRepository.apply.createTable
}
