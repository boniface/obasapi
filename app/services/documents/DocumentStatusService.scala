package services.documents

import domain.documents.DocumentStatus
import services.CrudService
import services.documents.Impl.cockroachdb.DocumentStatusServiceImpl

import scala.concurrent.Future

trait DocumentStatusService extends CrudService[DocumentStatus] {
  def getEntitiesForDocumentnStatus(documentId: String, statusId: String): Future[Seq[DocumentStatus]]
  def getEntitiesForDocument(documentId: String): Future[Seq[DocumentStatus]]
  def getLatestForDocumentnStatus(documentId: String, statusId: String): Future[Option[DocumentStatus]]
  def getLatestForDocument(documentId: String): Future[Option[DocumentStatus]]
}

object DocumentStatusService {
  def apply: DocumentStatusService = new DocumentStatusServiceImpl()
}
