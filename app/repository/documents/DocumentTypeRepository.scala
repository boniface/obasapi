package repository.documents

import domain.documents.DocumentType
import repository.Repository
import repository.documents.Impl.cockcroachdb

trait DocumentTypeRepository extends Repository [DocumentType]{


}
object DocumentTypeRepository{
  def roach: DocumentTypeRepository = new cockcroachdb.DocumentTypeImpl()
}
