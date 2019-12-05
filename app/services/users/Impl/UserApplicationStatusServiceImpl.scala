package services.users.Impl

import domain.users.UserApplicationStatus
import repository.users.UserApplicationStatusRepository
import services.users.UserApplicationStatusService

import scala.concurrent.Future

class UserApplicationStatusServiceImpl extends UserApplicationStatusService {

  override def getEntitiesForApplication(applicationId: String): Future[Seq[UserApplicationStatus]] =
    UserApplicationStatusRepository.roach.getEntitiesForApplication(applicationId)

  override def saveEntity(entity: UserApplicationStatus): Future[Option[UserApplicationStatus]] =
    UserApplicationStatusRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplicationStatus]] =
    UserApplicationStatusRepository.roach.getEntities

  override def getEntity(id: String): Future[Option[UserApplicationStatus]] = ???

  override def deleteEntity(entity: UserApplicationStatus): Future[Boolean] = ???

  override def createTable: Future[Boolean] = UserApplicationStatusRepository.roach.createTable

  override def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[UserApplicationStatus]] =
    UserApplicationStatusRepository.roach.getEntitiesForAppnStatus(applicationId, statusId)

  override def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[UserApplicationStatus]] =
    UserApplicationStatusRepository.roach.getLatestForAppnStatus(applicationId, statusId)

  override def getLatestForApplication(applicationId: String): Future[Option[UserApplicationStatus]] =
    UserApplicationStatusRepository.roach.getLatestForApplication(applicationId)
}
