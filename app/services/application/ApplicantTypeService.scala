package services.application

import domain.application.ApplicantType
import services.CrudService
import services.application.Impl.cockroachdb

trait ApplicantTypeService extends CrudService[ApplicantType] {

}


object ApplicantTypeService
{
  def roach: ApplicantTypeService = new cockroachdb.ApplicantTypeServiceImpl()
}
