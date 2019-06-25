package services.address

import domain.address.AddressType
import services.CrudService
import services.address.Impl.AddressTypeServiceImpl

trait AddressTypeService extends CrudService[AddressType] {


}

  object AddressTypeService{

    def apply: AddressTypeServiceImpl = new AddressTypeServiceImpl()

  }