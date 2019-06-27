package repository.users.Impl.cockroachdb

import domain.users.UserDocuments
import repository.users.Impl.cockroachdb.tables.UserDocumentsTable
import repository.users.UserDocumentsRepository

import scala.concurrent.Future

class UserDocumentsRepositoryImpl  extends UserDocumentsRepository{

  override def saveEntity(entity: UserDocuments): Future[Boolean] = {
    Future.successful(UserDocumentsTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[UserDocuments]] = {
    UserDocumentsTable.getEntities
  }

  override def getEntity(userDocumentsId: String): Future[Option[UserDocuments]] = {
    UserDocumentsTable.getEntity(userDocumentsId)
  }

  override def deleteEntity(entity: UserDocuments): Future[Boolean] = {
    Future.successful(UserDocumentsTable.deleteEntity(entity.userDocumentsId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserDocumentsTable.createTable)
  }
}
