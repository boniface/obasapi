package repository.users.impl.cockroachdb

import domain.users.UserCommunication
import repository.users.UserCommunicationRepository
import repository.users.impl.cockroachdb.tables.UserCommunicationTable

import scala.concurrent.Future

class UserCommunicationRepositoryImpl  extends UserCommunicationRepository{

  override def saveEntity(entity: UserCommunication): Future[Boolean] = {
    Future.successful(UserCommunicationTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserCommunication]] = {
    UserCommunicationTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserCommunication]] = {
    UserCommunicationTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserCommunication): Future[Boolean] = {
    Future.successful(UserCommunicationTable.deleteEntity(entity.communicationId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserCommunicationTable.createTable)
  }
}
