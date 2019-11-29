package services.application

import domain.application.Application
import services.CrudService
import services.application.Impl.cockroachdb.ApplicationServiceImpl

trait ApplicationService extends CrudService[Application]{

}

object ApplicationService {
  def apply: ApplicationService = new ApplicationServiceImpl()
}
