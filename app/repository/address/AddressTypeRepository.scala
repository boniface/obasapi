package repository.address
import domain.address.AddressType
import repository.Repository
import repository.address.impl.cockcroachdb

trait  AddressTypeRepository extends Repository [AddressType]{

}

object AddressTypeRepository{

  def roach: AddressTypeRepository = new cockcroachdb.AddressTypeRepositoryImpl()

}
