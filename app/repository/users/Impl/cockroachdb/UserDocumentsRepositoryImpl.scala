package repository.users.Impl.cockroachdb

import domain.users.UserDocuments
import repository.users.Impl.cockroachdb.tables.UserDocumentsTable
import repository.users.UserDocumentsRepository

import scala.concurrent.Future

class UserDocumentsRepositoryImpl  extends UserDocumentsRepository{

  override def saveEntity(entity: UserDocuments): Future[Boolean] = {
    UserDocumentsRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserDocuments]] = {
    UserDocumentsRepository.roach.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserDocuments]] = {
    UserDocumentsRepository.roach.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserDocuments): Future[Boolean] = {
    UserDocumentsRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    UserDocumentsRepository.roach.createTable
  }
}
