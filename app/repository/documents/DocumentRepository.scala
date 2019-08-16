package repository.documents

import domain.documents.Document
import repository.Repository
import repository.documents.impl.cockcroachdb.DocumentRepositoryImpl

trait DocumentRepository extends Repository [Document]{

}
object DocumentRepository{

  def roach: DocumentRepository = new DocumentRepositoryImpl()
}
