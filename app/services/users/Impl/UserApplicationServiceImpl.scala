package services.users.Impl

import domain.users.UserApplication
import repository.users.UserApplicationRepository
import services.users.UserApplicationService

import scala.concurrent.Future

class UserApplicationServiceImpl extends  UserApplicationService {
  override def getEntity(id: String, applicationId: String): Future[Option[UserApplication]] =
    UserApplicationRepository.roach.getEntity(id, applicationId)

  override def getEntitiesForUser(id: String): Future[Seq[UserApplication]] =
    UserApplicationRepository.roach.getEntitiesForUser(id)

  override def saveEntity(entity: UserApplication): Future[Option[UserApplication]] =
    UserApplicationRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplication]] = UserApplicationRepository.roach.getEntities

  override def getEntity(id: String): Future[Option[UserApplication]] = ???

  override def deleteEntity(entity: UserApplication): Future[Boolean] =
    UserApplicationRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserApplicationRepository.roach.createTable
}
