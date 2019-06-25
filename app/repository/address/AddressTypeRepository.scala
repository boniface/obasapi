package repository.address
import domain.address.AddressType
import repository.Repository
import repository.address.Impl.cassandra.AddressTypeRepositoryImpl

trait AddressTypeRepository extends Repository [AddressType]{

}

object AddressTypeRepository{

  def apply: AddressTypeRepositoryImpl = new AddressTypeRepositoryImpl()

}
