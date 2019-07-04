package services.documents.Impl.cockroachdb

import domain.documents.DocumentType
import services.documents.DocumentTypeService

import scala.concurrent.Future

class DocumentTypeServiceImpl extends DocumentTypeService{

  override def saveEntity(entity: DocumentType): Future[Boolean] = {
    DocumentTypeService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[DocumentType]] = {
    DocumentTypeService.roach.getEntities
  }

  override def getEntity(documentTypeId: String): Future[Option[DocumentType]] = {
    DocumentTypeService.roach.getEntity(documentTypeId)
  }

  override def deleteEntity(entity: DocumentType): Future[Boolean] = {
    DocumentTypeService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    DocumentTypeService.roach.createTable
  }
}
