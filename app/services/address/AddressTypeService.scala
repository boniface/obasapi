package services.address

import domain.address.AddressType
import javax.swing.text.html.parser.Entity
import services.CrudService

import services.address.Impl.AddressTypeServiceImpl

import scala.concurrent.Future

trait AddressTypeService extends CrudService[AddressType] {




}

  object AddressTypeService{

    def apply: AddressTypeService = new AddressTypeServiceImpl()

  }