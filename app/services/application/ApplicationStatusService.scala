package services.application

import domain.application.ApplicationStatus
import services.CrudService
import services.application.Impl.cockroachdb.ApplicationStatusServiceImpl

trait ApplicationStatusService extends CrudService[ApplicationStatus]{
}

object ApplicationStatusService{
  def roach: ApplicationStatusService = new ApplicationStatusServiceImpl()
}
