package services.institutions

import domain.institutions.School
import services.CrudService
import services.institutions.Impl.cockroachdb.SchoolServiceImpl

trait SchoolService extends CrudService[School]{

}

object SchoolService{
  def roach: SchoolService = new SchoolServiceImpl()
}
