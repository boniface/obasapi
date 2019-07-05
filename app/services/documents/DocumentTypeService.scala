package services.documents

import domain.documents.DocumentType
import services.CrudService
import services.documents.Impl.cockroachdb

trait DocumentTypeService extends CrudService[DocumentType]{

}

object DocumentTypeService
{
  def roach: DocumentTypeService = new cockroachdb.DocumentTypeServiceImpl()
}
