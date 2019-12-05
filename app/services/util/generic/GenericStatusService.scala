package services.util.generic

import domain.util.generic.GenericStatus
import services.CrudService
import services.util.generic.impl.cockroach.GenericStatusServiceImpl

trait GenericStatusService extends CrudService[GenericStatus]{
}

object GenericStatusService{
  def roach: GenericStatusService = new GenericStatusServiceImpl()
}
