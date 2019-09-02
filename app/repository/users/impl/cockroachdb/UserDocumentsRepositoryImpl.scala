package repository.users.impl.cockroachdb

import domain.users.UserDocuments
import repository.users.impl.cockroachdb.tables.UserDocumentsTable
import repository.users.UserDocumentsRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserDocumentsRepositoryImpl  extends UserDocumentsRepository{

  override def saveEntity(entity: UserDocuments): Future[Option[UserDocuments]] = {
    UserDocumentsTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserDocuments]] = {
    UserDocumentsTable.getEntities
  }

  override def getEntity(userDocumentsId: String): Future[Option[UserDocuments]] = {
    UserDocumentsTable.getEntity(userDocumentsId)
  }

  override def deleteEntity(entity: UserDocuments): Future[Boolean] = {
    UserDocumentsTable.deleteEntity(entity.userDocumentsId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserDocumentsTable.createTable)
  }
}


