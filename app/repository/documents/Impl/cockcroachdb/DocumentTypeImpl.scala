package repository.documents.Impl.cockcroachdb

import domain.documents.DocumentType
import repository.documents.DocumentTypeRepository


import scala.concurrent.Future

class DocumentTypeImpl extends DocumentTypeRepository{
  override def saveEntity(entity: DocumentType): Future[Boolean] ={
    DocumentTypeRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[DocumentType]] = {
   DocumentTypeRepository.roach.getEntities
  }

  override def getEntity(documentTypeId: String): Future[Option[DocumentType]] = {
   DocumentTypeRepository.roach.getEntity(documentTypeId)
  }

  override def deleteEntity(entity: DocumentType): Future[Boolean] = {
    DocumentTypeRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
   DocumentTypeRepository.roach.createTable
  }
}
