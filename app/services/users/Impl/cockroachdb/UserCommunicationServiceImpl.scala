package services.users.Impl.cockroachdb

import domain.users.UserCommunication
import services.users.UserCommunicationService

import scala.concurrent.Future

class UserCommunicationServiceImpl extends UserCommunicationService {

  override def saveEntity(entity: UserCommunication): Future[Boolean] =
    UserCommunicationService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserCommunication]] =
    UserCommunicationService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserCommunication]] =
    UserCommunicationService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserCommunication): Future[Boolean] =
    UserCommunicationService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserCommunicationService.roach.createTable

}
