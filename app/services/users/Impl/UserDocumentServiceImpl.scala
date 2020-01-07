package services.users.Impl

import domain.users.UserDocument
import repository.users.UserDocumentRepository
import services.users.UserDocumentService

import scala.concurrent.Future

class UserDocumentServiceImpl extends UserDocumentService {

  override def saveEntity(entity: UserDocument): Future[Option[UserDocument]] =
    UserDocumentRepository.roach.saveEntity(entity)

  override def getEntities: Future[Seq[UserDocument]] =
    UserDocumentRepository.roach.getEntities

  override def getEntity(userDocumentsId: String): Future[Option[UserDocument]] = ???

  override def deleteEntity(entity: UserDocument): Future[Boolean] =
    UserDocumentRepository.roach.deleteEntity(entity)

  override def createTable: Future[Boolean] =
    UserDocumentRepository.roach.createTable

  override def getEntity(userId: String, documentId: String): Future[Option[UserDocument]] =
    UserDocumentRepository.roach.getEntity(userId, documentId)

  override def getUserDocuments(userId: String): Future[Seq[UserDocument]] =
    UserDocumentRepository.roach.getUserDocuments(userId)
}
