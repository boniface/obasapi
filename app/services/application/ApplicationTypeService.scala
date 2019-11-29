package services.application

import domain.application.ApplicationType
import services.CrudService
import services.application.Impl.cockroachdb.ApplicationTypeServiceImpl

trait ApplicationTypeService extends CrudService[ApplicationType]{

}

object ApplicationTypeService {
  def apply: ApplicationTypeService = new ApplicationTypeServiceImpl()
}
