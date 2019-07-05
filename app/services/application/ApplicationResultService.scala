package services.application

import domain.application.ApplicationResult
import services.CrudService
import services.application.Impl.cockroachdb

trait ApplicationResultService extends CrudService[ApplicationResult]{

}

 object ApplicationResultService
{
  def roach: ApplicationResultService = new cockroachdb.ApplicationResultServiceImpl()
}
