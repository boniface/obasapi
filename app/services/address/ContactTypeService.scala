package services.address

import domain.address.ContactType
import services.CrudService
import services.address.ContactTypeService
import services.address.Impl.cockroachdb

trait ContactTypeService extends CrudService [ContactType ]{


}

object ContactTypeService{
    def roach: ContactTypeService = new cockroachdb.ContactTypeServiceImpl()

  }