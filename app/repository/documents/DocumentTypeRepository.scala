package repository.documents

import domain.documents.DocumentType
import repository.Repository
import repository.documents.Impl.cassandra.DocumentTypeRepositoryImpl

trait DocumentTypeRepository extends Repository [DocumentType]{


}
object DocumentTypeRepository{
  def apply: DocumentTypeRepositoryImpl = new DocumentTypeRepositoryImpl()
}
