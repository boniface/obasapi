package repository.documents.impl.cockcroachdb

import domain.documents.Document
import repository.documents.DocumentRepository
import repository.documents.impl.cockcroachdb.tables.DocumentTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class DocumentRepositoryImpl extends DocumentRepository{
  override def saveEntity(entity: Document): Future[Boolean] = {
    DocumentTable.saveEntity(entity).map(value=> value.equals(entity))
  }

  override def getEntities: Future[Seq[Document]] = {
    DocumentTable.getEntities
  }

  override def getEntity(email: String): Future[Option[Document]] = {
    DocumentTable.getEntity(email)
  }

  override def deleteEntity(entity: Document): Future[Boolean] = {
    DocumentTable.deleteEntity(entity.documentsId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(DocumentTable.createTable)
  }
}

