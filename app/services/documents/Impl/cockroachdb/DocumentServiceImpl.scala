package services.documents.Impl.cockroachdb

import domain.documents.Document
import services.documents.DocumentService

import scala.concurrent.Future

class DocumentServiceImpl extends DocumentService{

  override def saveEntity(entity: Document): Future[Boolean] = {
    DocumentService.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Document]] = {
    DocumentService.roach.getEntities
  }

  override def getEntity(email: String): Future[Option[Document]] = {
    DocumentService.roach.getEntity(email)
  }

  override def deleteEntity(entity: Document): Future[Boolean] = {
    DocumentService.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    DocumentService.roach.createTable
  }
}
