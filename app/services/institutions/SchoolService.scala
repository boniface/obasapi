package services.institutions

import domain.institutions.School
import services.CrudService
import services.institutions.Impl.cockroachdb

trait SchoolService extends CrudService[School]{

}

object SchoolService{
  def roach: SchoolService = new cockroachdb.SchoolServiceImpl()
}
