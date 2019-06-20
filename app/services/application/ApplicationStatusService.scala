package services.application

import domain.application.ApplicationStatus
import services.CrudService
import services.application.Impl.ApplicationStatusServiceImpl

trait ApplicationStatusService extends CrudService[ApplicationStatus]{



}

object ApplicationStatusService{

  def apply: ApplicationStatusServiceImpl = new ApplicationStatusServiceImpl()
}
