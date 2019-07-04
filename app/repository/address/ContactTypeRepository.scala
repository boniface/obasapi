package repository.address

import domain.address.ContactType
import repository.Repository
import repository.address.Impl.cockcroachdb

trait ContactTypeRepository extends Repository [ContactType]{

}
object ContactTypeRepository{
  def roach: ContactTypeRepository = new cockcroachdb.ContactTypeRepositoryImpl()
}
