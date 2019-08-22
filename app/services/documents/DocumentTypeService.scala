package services.documents

import domain.documents.DocumentType
import services.CrudService
import services.documents.Impl.cockroachdb.DocumentTypeServiceImpl

trait DocumentTypeService extends CrudService[DocumentType]{

}

object DocumentTypeService
{
  def roach: DocumentTypeService = new DocumentTypeServiceImpl()
}
