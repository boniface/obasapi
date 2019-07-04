package services.documents.Impl

import domain.documents.DocumentType
import services.documents.DocumentTypeService

import scala.concurrent.Future

class DocumentTypeServiceImpl extends DocumentTypeService{

  override def saveEntity(entity: DocumentType): Future[Boolean] = ???

  override def getEntities: Future[Seq[DocumentType]] = ???

  override def getEntity(documentTypeId: String): Future[Option[DocumentType]] = ???

  override def deleteEntity(entity: DocumentType): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
