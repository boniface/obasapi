package services.application.Impl.cockroachdb

import domain.application.ApplicationType
import repository.application.ApplicationTypeRepository
import services.application.ApplicationTypeService

import scala.concurrent.Future

class ApplicationTypeServiceImpl extends ApplicationTypeService {
  override def saveEntity(entity: ApplicationType): Future[Option[ApplicationType]] =
    ApplicationTypeRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[ApplicationType]] =
    ApplicationTypeRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[ApplicationType]] =
    ApplicationTypeRepository.apply.getEntity(id)

  override def deleteEntity(entity: ApplicationType): Future[Boolean] =
    ApplicationTypeRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    ApplicationTypeRepository.apply.createTable
}
