package services.documents.Impl

import domain.documents.Document
import services.documents.DocumentService

import scala.concurrent.Future

class DocumentServiceImpl extends DocumentService{

  override def saveEntity(entity: Document): Future[Boolean] = ???

  override def getEntities: Future[Seq[Document]] = ???

  override def getEntity(email: String): Future[Option[Document]] = ???

  override def deleteEntity(entity: Document): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
