package repository.address

import domain.address.ContactType
import repository.Repository
import repository.address.Impl.cassandra.ContactTypeRepositoryImpl

trait ContactTypeRepository extends Repository [ContactType]{

}
object ContactTypeRepository{
  def apply: ContactTypeRepositoryImpl = new ContactTypeRepositoryImpl()
}
