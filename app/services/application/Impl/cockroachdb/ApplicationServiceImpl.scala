package services.application.Impl.cockroachdb

import domain.application.Application
import repository.application.ApplicationRepository
import services.application.ApplicationService

import scala.concurrent.Future

class ApplicationServiceImpl extends ApplicationService {
  override def saveEntity(entity: Application): Future[Option[Application]] =
    ApplicationRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[Application]] = ApplicationRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[Application]] = ApplicationRepository.apply.getEntity(id)

  override def deleteEntity(entity: Application): Future[Boolean] = ApplicationRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = ApplicationRepository.apply.createTable
}
