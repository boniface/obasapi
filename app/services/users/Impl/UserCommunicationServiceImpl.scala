package services.users.Impl

import domain.users.UserCommunication
import repository.users.UserCommunicationRepository
import services.users.UserCommunicationService

import scala.concurrent.Future

class UserCommunicationServiceImpl extends UserCommunicationService {

  override def saveEntity(entity: UserCommunication): Future[Boolean] =
    UserCommunicationRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserCommunication]] =
    UserCommunicationRepository.roach.getEntities

  override def getEntity(communicationId: String): Future[Option[UserCommunication]] =
    UserCommunicationRepository.roach.getEntity(communicationId)

  override def deleteEntity(entity: UserCommunication): Future[Boolean] =
    UserCommunicationRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserCommunicationRepository.roach.createTable

}
