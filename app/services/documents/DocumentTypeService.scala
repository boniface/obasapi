package services.documents

import domain.documents.DocumentType
import services.CrudService
import services.documents.Impl.DocumentTypeServiceImpl

trait DocumentTypeService extends CrudService[DocumentType]{

}

object DocumentTypeService
{
  def apply: DocumentTypeService = new DocumentTypeServiceImpl()
}
