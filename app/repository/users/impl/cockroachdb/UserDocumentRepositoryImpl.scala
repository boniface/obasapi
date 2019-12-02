package repository.users.impl.cockroachdb

import domain.users.UserDocument
import repository.users.impl.cockroachdb.tables.{UserDocumentTable, UserDocumentTableCreate}
import repository.users.UserDocumentRepository

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserDocumentRepositoryImpl  extends UserDocumentRepository{

  override def saveEntity(entity: UserDocument): Future[Option[UserDocument]] = {
    UserDocumentTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[UserDocument]] = {
    UserDocumentTable.getEntities
  }

  override def getEntity(userId: String, documentId: String): Future[Option[UserDocument]] = {
    UserDocumentTable.getEntity(userId, documentId)
  }

  override def deleteEntity(entity: UserDocument): Future[Boolean] = {
    UserDocumentTable.deleteEntity(entity.userId, entity.documentId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(UserDocumentTableCreate.createTable)
  }

  override def getUserDocuments(userId: String): Future[Seq[UserDocument]] = UserDocumentTable.getUserDocuments(userId)

  override def getEntity(id: String): Future[Option[UserDocument]] = ???
}


