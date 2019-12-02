package repository.documents.impl.cockcroachdb

import domain.documents.Document
import repository.documents.DocumentRepository
import repository.documents.impl.cockcroachdb.tables.DocumentTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class DocumentRepositoryImpl extends DocumentRepository{
  override def saveEntity(entity: Document): Future[Option[Document]] = {
    DocumentTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Document]] = {
    DocumentTable.getEntities
  }

  override def getEntity(documentsId: String): Future[Option[Document]] = {
    DocumentTable.getEntity(documentsId)
  }

  override def deleteEntity(entity: Document): Future[Boolean] = {
    DocumentTable.deleteEntity(entity.documentId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(DocumentTable.createTable)
  }
}

