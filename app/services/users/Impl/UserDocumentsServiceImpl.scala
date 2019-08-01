package services.users.Impl

import domain.users.UserDocuments
import repository.users.UserDocumentsRepository
import services.users.UserDocumentsService

import scala.concurrent.Future

class UserDocumentsServiceImpl extends UserDocumentsService {

  override def saveEntity(entity: UserDocuments): Future[Option[UserDocuments]] =
    UserDocumentsRepository.roach.saveEntity(entity)
  override def getEntities: Future[Seq[UserDocuments]] =
    UserDocumentsRepository.roach.getEntities

  override def getEntity(userDocumentsId: String): Future[Option[UserDocuments]] =
    UserDocumentsRepository.roach.getEntity(userDocumentsId)

  override def deleteEntity(entity: UserDocuments): Future[Boolean] =
    UserDocumentsRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserDocumentsRepository.roach.createTable

}
