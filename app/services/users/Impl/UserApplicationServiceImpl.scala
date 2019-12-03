package services.users.Impl

import domain.users.UserApplication
import repository.users.UserApplicationRepository
import services.users.UserApplicationService

import scala.concurrent.Future

class UserApplicationServiceImpl extends  UserApplicationService {

  override def getEntity(userId: String, addressTypeId: String): Future[Option[UserApplication]] = {
    UserApplicationRepository.roach.getEntity(userId,addressTypeId)
  }

  override def getEntityForUser(userId: String): Future[Seq[UserApplication]] = {
    UserApplicationRepository.roach.getEntityForUser(userId)
  }

  override def saveEntity(entity: UserApplication): Future[Option[UserApplication]] = {
    UserApplicationRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserApplication]] = {
    UserApplicationRepository.roach.getEntities
  }

  override def getEntity(id: String): Future[Option[UserApplication]] = ???

  override def deleteEntity(entity: UserApplication): Future[Boolean] = {
    UserApplicationRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserApplicationRepository.roach.createTable
  }
}
