package services.address

import domain.address.ContactType
import services.CrudService

trait ContactTypeService extends CrudService [ContactType ]{


}

object ContactTypeService{
    def roach: ContactTypeService = new cockroachdb.ContactTypeServiceImpl()

  }