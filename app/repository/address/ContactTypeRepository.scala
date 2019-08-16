package repository.address

import domain.address.ContactType
import repository.Repository
import repository.address.impl.cockcroachdb.ContactTypeRepositoryImpl

trait ContactTypeRepository extends Repository [ContactType]{

}
object ContactTypeRepository{
  def roach: ContactTypeRepository = new ContactTypeRepositoryImpl()
}
