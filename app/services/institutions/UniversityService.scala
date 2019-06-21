package services.institutions

import domain.institutions.University
import services.CrudService
import services.institutions.Impl.UniversityServiceImpl

trait UniversityService extends CrudService[University]{

}

object UniversityService{
  def apply: UniversityService = new UniversityServiceImpl()
}
