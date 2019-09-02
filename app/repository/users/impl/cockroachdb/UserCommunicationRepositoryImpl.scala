package repository.users.impl.cockroachdb

import domain.users.UserCommunication
import repository.users.UserCommunicationRepository
import repository.users.impl.cockroachdb.tables.UserCommunicationTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserCommunicationRepositoryImpl  extends UserCommunicationRepository{

  override def saveEntity(entity: UserCommunication): Future[Option[UserCommunication]] = {
    UserCommunicationTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserCommunication]] = {
    UserCommunicationTable.getEntities
  }

  override def getEntity(communicationId: String): Future[Option[UserCommunication]] = {
    UserCommunicationTable.getEntity(communicationId)
  }

  override def deleteEntity(entity: UserCommunication): Future[Boolean] = {
    UserCommunicationTable.deleteEntity(entity.communicationId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserCommunicationTable.createTable)
  }
}


