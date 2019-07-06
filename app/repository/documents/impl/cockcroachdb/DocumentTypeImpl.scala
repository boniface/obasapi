package repository.documents.impl.cockcroachdb

import domain.documents.DocumentType
import repository.documents.DocumentTypeRepository
import repository.documents.impl.cockcroachdb.tables.DocumentTypeTable

import scala.concurrent.Future

class DocumentTypeImpl extends DocumentTypeRepository{
  override def saveEntity(entity: DocumentType): Future[Boolean] ={
    Future.successful(DocumentTypeTable.saveEntity(entity).isCompleted)
  }

  override def getEntities: Future[Seq[DocumentType]] = {
    DocumentTypeTable.getEntities
  }

  override def getEntity(documentTypeId: String): Future[Option[DocumentType]] = {
    DocumentTypeTable.getEntity(documentTypeId)
  }

  override def deleteEntity(entity: DocumentType): Future[Boolean] = {
    Future.successful(DocumentTypeTable.deleteEntity(entity.documentTypeId).isCompleted)
  }

  override def createTable: Future[Boolean] = {
    Future.successful(DocumentTypeTable.createTable)
  }
}
