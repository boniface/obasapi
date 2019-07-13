package services.documents.Impl.cockroachdb

import domain.documents.Document
import repository.documents.DocumentRepository
import services.documents.DocumentService

import scala.concurrent.Future

class DocumentServiceImpl extends DocumentService{

  override def saveEntity(entity: Document): Future[Boolean] = {
    DocumentRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Document]] = {
    DocumentRepository.roach.getEntities
  }

  override def getEntity(documentsId: String): Future[Option[Document]] = {
    DocumentRepository.roach.getEntity(documentsId)
  }

  override def deleteEntity(entity: Document): Future[Boolean] = {
    DocumentRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    DocumentRepository.roach.createTable
  }
}

