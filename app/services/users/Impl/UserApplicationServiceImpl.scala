package services.users.Impl

import domain.users.UserApplication
import repository.users.UserApplicationRepository
import services.users.UserApplicationService

import scala.concurrent.Future

class UserApplicationServiceImpl extends  UserApplicationService {

  override def saveEntity(entity: UserApplication): Future[Option[UserApplication]] =
    UserApplicationRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplication]] =
    UserApplicationRepository.roach.getEntities

  override def getEntity(userApplicationResultId: String): Future[Option[UserApplication]] =
    UserApplicationRepository.roach.getEntity(userApplicationResultId)

  override def deleteEntity(entity: UserApplication): Future[Boolean] =
    UserApplicationRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserApplicationRepository.roach.createTable

}
