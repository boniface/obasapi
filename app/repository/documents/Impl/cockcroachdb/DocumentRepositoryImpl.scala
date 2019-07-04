package repository.documents.Impl.cockcroachdb

import domain.documents.Document
import repository.documents.DocumentRepository


import scala.concurrent.Future

class DocumentRepositoryImpl extends DocumentRepository{
  override def saveEntity(entity: Document): Future[Boolean] = {
    DocumentRepository.roach.saveEntity(entity)
  }

  override def getEntities: Future[Seq[Document]] = {
    DocumentRepository.roach.getEntities
  }

  override def getEntity(email: String): Future[Option[Document]] = {
   DocumentRepository.roach.getEntity(email)
  }

  override def deleteEntity(entity: Document): Future[Boolean] = {
    DocumentRepository.roach.deleteEntity(entity)
  }

  override def createTable: Future[Boolean] = {
    DocumentRepository.roach.createTable
  }
}
