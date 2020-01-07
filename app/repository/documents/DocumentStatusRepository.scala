package repository.documents

import domain.documents.DocumentStatus
import repository.Repository
import repository.documents.impl.cockcroachdb.DocumentStatusRepositoryImpl

import scala.concurrent.Future

trait DocumentStatusRepository extends Repository[DocumentStatus] {
  def getEntitiesForDocumentnStatus(documentId: String, statusId: String): Future[Seq[DocumentStatus]]
  def getEntitiesForDocument(documentId: String): Future[Seq[DocumentStatus]]
  def getLatestForDocumentnStatus(documentId: String, statusId: String): Future[Option[DocumentStatus]]
  def getLatestForDocument(documentId: String): Future[Option[DocumentStatus]]
}

object DocumentStatusRepository {
  def apply: DocumentStatusRepository = new DocumentStatusRepositoryImpl()
}
