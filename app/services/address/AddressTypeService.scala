package services.address

import domain.address.AddressType
import services.CrudService
import services.address.Impl.cockroachdb

trait AddressTypeService extends CrudService[AddressType] {


}

  object AddressTypeService{

    def roach: AddressTypeService = new cockroachdb.AddressTypeServiceImpl()

  }