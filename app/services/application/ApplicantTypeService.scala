package services.application

import domain.application.ApplicantType
import services.CrudService
import services.application.ApplicantTypeService
import services.application.Impl.ApplicantTypeServiceImpl

trait ApplicantTypeService extends CrudService[ApplicantType] {

}


object ApplicantTypeService
{
  def apply: ApplicantTypeServiceImpl = new ApplicantTypeServiceImpl()
}
