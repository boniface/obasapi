package repository.users.impl.cockroachdb

import domain.users.UserDocuments
import repository.users.impl.cockroachdb.tables.UserDocumentsTable
import repository.users.UserDocumentsRepository

import scala.concurrent.Future

class UserDocumentsRepositoryImpl  extends UserDocumentsRepository{

  override def saveEntity(entity: UserDocuments): Future[Boolean] = {
    Future.successful(UserDocumentsTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserDocuments]] = {
    UserDocumentsTable.getEntities
  }

  override def getEntity(userContactId: String): Future[Option[UserDocuments]] = {
    UserDocumentsTable.getEntity(userContactId)
  }

  override def deleteEntity(entity: UserDocuments): Future[Boolean] = {
    Future.successful(UserDocumentsTable.deleteEntity(entity.userDocumentsId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserDocumentsTable.createTable)
  }
}
