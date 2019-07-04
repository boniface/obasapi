package repository.documents.Impl.cockcroachdb

import domain.documents.Document
import repository.documents.DocumentRepository
import repository.documents.Impl.cockcroachdb.tables.DocumentTable

import scala.concurrent.Future

class DocumentRepositoryImpl extends DocumentRepository{
  override def saveEntity(entity: Document): Future[Boolean] = {
    Future.successful(DocumentTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[Document]] = {
    DocumentTable.getEntities
  }

  override def getEntity(email: String): Future[Option[Document]] = {
    DocumentTable.getEntity(email)
  }

  override def deleteEntity(entity: Document): Future[Boolean] = {
    Future.successful(DocumentTable.deleteEntity(entity.email).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(DocumentTable.createTable)
  }
}
