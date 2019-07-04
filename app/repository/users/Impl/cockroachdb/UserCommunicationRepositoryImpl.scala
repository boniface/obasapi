package repository.users.Impl.cockroachdb

import domain.users.UserCommunication
import repository.users.UserCommunicationRepository

import scala.concurrent.Future

class UserCommunicationRepositoryImpl  extends UserCommunicationRepository{

  override def saveEntity(entity: UserCommunication): Future[Boolean] = {
    UserCommunicationRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserCommunication]] = {
    UserCommunicationRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserCommunication]] = {
    UserCommunicationRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserCommunication): Future[Boolean] = {
    UserCommunicationRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserCommunicationRepository.roach.createTable
  }
}
