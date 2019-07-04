package repository.users.Impl.cockroachdb

import domain.users.UserCommunication
import repository.users.Impl.cockroachdb.tables.UserCommunicationTable
import repository.users.UserCommunicationRepository

import scala.concurrent.Future

class UserCommunicationRepositoryImpl  extends UserCommunicationRepository{

  override def saveEntity(entity: UserCommunication): Future[Boolean] = {
    Future.successful(UserCommunicationTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserCommunication]] = {
    UserCommunicationTable.getEntities
  }

  override def getEntity(communicationId: String): Future[Option[UserCommunication]] = {
    UserCommunicationTable.getEntity(communicationId)
  }

  override def deleteEntity(entity: UserCommunication): Future[Boolean] = {
    Future.successful(UserCommunicationTable.deleteEntity(entity.communicationId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserCommunicationTable.createTable)
  }
}
