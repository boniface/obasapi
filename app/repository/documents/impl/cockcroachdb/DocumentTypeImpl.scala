package repository.documents.impl.cockcroachdb

import domain.documents.DocumentType
import repository.documents.DocumentTypeRepository
import repository.documents.impl.cockcroachdb.tables.DocumentTypeTable

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class DocumentTypeImpl extends DocumentTypeRepository{
  override def saveEntity(entity: DocumentType): Future[Option[DocumentType]] = {
    DocumentTypeTable.saveEntity(entity)
  }

  override def getEntities: Future[Seq[DocumentType]] = {
    DocumentTypeTable.getEntities
  }

  override def getEntity(documentTypeId: String): Future[Option[DocumentType]] = {
    DocumentTypeTable.getEntity(documentTypeId)
  }

  override def deleteEntity(entity: DocumentType): Future[Boolean] = {
    DocumentTypeTable.deleteEntity(entity.documentTypeId)map(value=> value.isValidInt)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(DocumentTypeTable.createTable)
  }
}

