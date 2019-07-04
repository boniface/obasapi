package repository.address
import domain.address.AddressType
import repository.Repository
import repository.address.Impl.cockcroachdb

trait  AddressTypeRepository extends Repository [AddressType]{

}

object AddressTypeRepository{

  def roach: AddressTypeRepository = new cockcroachdb.AddressTypeRepositoryImpl()

}
