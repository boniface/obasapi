package services.institutions

import domain.institutions.University
import services.CrudService
import services.institutions.Impl.cockroachdb

trait UniversityService extends CrudService[University]{

}

object UniversityService{
  def roach: UniversityService = new cockroachdb.UniversityServiceImpl()
}
