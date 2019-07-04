package services.application

import domain.application.ApplicationResult
import services.CrudService
import services.application.Impl.ApplicationResultServiceImpl


trait ApplicationResultService extends CrudService[ApplicationResult]{

}

 object ApplicationResultService
{
  def apply: ApplicationResultService = new ApplicationResultServiceImpl()
}
