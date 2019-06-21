package services.documents

import domain.documents.Document
import services.CrudService
import services.documents.Impl.DocumentServiceImpl

trait DocumentService extends CrudService[Document]{

}

object DocumentService
{
  def apply: DocumentServiceImpl = new DocumentServiceImpl()
}
