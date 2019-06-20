package services.documents.Impl

import domain.documents.Document
import services.CrudService

import scala.concurrent.Future

class DocumentServiceImpl extends CrudService[Document]{

  override def saveEntity(entity: Document): Future[Boolean] = ???

  override def getEntities: Future[Seq[Document]] = ???

  override def getEntity(id: String): Future[Option[Document]] = ???

  override def deleteEntity(entity: Document): Future[Boolean] = ???

  override def createTable: Future[Boolean] = ???
}
