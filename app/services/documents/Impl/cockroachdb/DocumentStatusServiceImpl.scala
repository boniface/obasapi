package services.documents.Impl.cockroachdb

import domain.documents.DocumentStatus
import repository.documents.DocumentStatusRepository
import services.documents.DocumentStatusService

import scala.concurrent.Future

class DocumentStatusServiceImpl extends DocumentStatusService {
  override def getEntitiesForDocumentnStatus(documentId: String, statusId: String): Future[Seq[DocumentStatus]] =
    DocumentStatusRepository.apply.getEntitiesForDocumentnStatus(documentId, statusId)

  override def getEntitiesForDocument(documentId: String): Future[Seq[DocumentStatus]] =
    DocumentStatusRepository.apply.getEntitiesForDocument(documentId)

  override def getLatestForDocumentnStatus(documentId: String, statusId: String): Future[Option[DocumentStatus]] =
    DocumentStatusRepository.apply.getLatestForDocumentnStatus(documentId, statusId)

  override def getLatestForDocument(documentId: String): Future[Option[DocumentStatus]] =
    DocumentStatusRepository.apply.getLatestForDocument(documentId)

  override def saveEntity(entity: DocumentStatus): Future[Option[DocumentStatus]] =
    DocumentStatusRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[DocumentStatus]] = DocumentStatusRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[DocumentStatus]] = ???

  override def deleteEntity(entity: DocumentStatus): Future[Boolean] = ???

  override def createTable: Future[Boolean] = DocumentStatusRepository.apply.createTable
}
