package repository.documents

import domain.documents.Document
import repository.Repository
import repository.documents.Impl.cassandra.DocumentRepositoryImpl

trait DocumentRepository extends Repository [Document]{

}
object DocumentRepository{

  def apply: DocumentRepository = new DocumentRepositoryImpl()
}
