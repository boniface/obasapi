package services.demographics

import domain.demographics.Gender
import services.CrudService
import services.demographics.Impl.GenderServiceImpl

trait GenderService extends CrudService[Gender]{

}

object GenderService
{
  def apply: GenderService = new GenderServiceImpl()
}
