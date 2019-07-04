package services.users.Impl.cockroachdb

import domain.users.UserDocuments
import services.users.UserDocumentsService

import scala.concurrent.Future

class UserDocumentsServiceImpl extends UserDocumentsService {

  override def saveEntity(entity: UserDocuments): Future[Boolean] =
    UserDocumentsService.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserDocuments]] =
    UserDocumentsService.roach.getEntities

  override def getEntity(userAddressId: String): Future[Option[UserDocuments]] =
    UserDocumentsService.roach.getEntity(userAddressId)

  override def deleteEntity(entity: UserDocuments): Future[Boolean] =
    UserDocumentsService.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserDocumentsService.roach.createTable

}
