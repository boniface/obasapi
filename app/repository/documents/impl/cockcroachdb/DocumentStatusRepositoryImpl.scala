package repository.documents.impl.cockcroachdb

import domain.documents.DocumentStatus
import repository.documents.DocumentStatusRepository
import repository.documents.impl.cockcroachdb.tables.{DocumentStatusTable, DocumentStatusTableCreate}

import scala.concurrent.Future

class DocumentStatusRepositoryImpl extends DocumentStatusRepository {
  override def getEntitiesForDocumentnStatus(documentId: String, statusId: String): Future[Seq[DocumentStatus]] =
    DocumentStatusTable.getEntitiesForDocumentnStatus(documentId, statusId)

  override def getEntitiesForDocument(documentId: String): Future[Seq[DocumentStatus]] =
    DocumentStatusTable.getEntitiesForDocument(documentId)

  override def getLatestForDocumentnStatus(documentId: String, statusId: String): Future[Option[DocumentStatus]] =
    DocumentStatusTable.getLatestForDocumentnStatus(documentId, statusId)

  override def getLatestForDocument(documentId: String): Future[Option[DocumentStatus]] =
    DocumentStatusTable.getLatestForDocument(documentId)

  override def saveEntity(entity: DocumentStatus): Future[Option[DocumentStatus]] =
    DocumentStatusTable.saveEntity(entity)

  override def getEntities: Future[Seq[DocumentStatus]] =
    DocumentStatusTable.getEntities

  override def getEntity(id: String): Future[Option[DocumentStatus]] = ???

  override def deleteEntity(entity: DocumentStatus): Future[Boolean] = ???

  override def createTable: Future[Boolean] =
    Future.successful(DocumentStatusTableCreate.createTable)
}
