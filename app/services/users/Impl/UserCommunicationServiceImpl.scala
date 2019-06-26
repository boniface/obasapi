package services.users.Impl

import domain.users.UserCommunication
import services.users.UserCommunicationService

import scala.concurrent.Future

class UserCommunicationServiceImpl extends UserCommunicationService {

  override def saveEntity(entity: UserCommunication): Future[Boolean] = ???

  override def getEntities: Future[Seq[UserCommunication]] = ???

  override def getEntity(communicationId: String): Future[Option[UserCommunication]] = ???

  override def deleteEntity(entity: UserCommunication): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???

}
