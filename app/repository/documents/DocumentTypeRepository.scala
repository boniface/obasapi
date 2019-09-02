package repository.documents

import domain.documents.DocumentType
import repository.Repository
import repository.documents.impl.cockcroachdb.DocumentTypeImpl

trait DocumentTypeRepository extends Repository [DocumentType]{


}
object DocumentTypeRepository{
  def roach: DocumentTypeRepository = new DocumentTypeImpl()
}
