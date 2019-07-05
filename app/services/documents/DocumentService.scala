package services.documents

import domain.documents.Document
import services.CrudService
import services.documents.Impl.cockroachdb

trait DocumentService extends CrudService[Document]{

}

object DocumentService
{
  def roach: DocumentService = new cockroachdb.DocumentServiceImpl()
}
