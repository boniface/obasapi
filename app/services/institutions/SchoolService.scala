package services.institutions

import domain.institutions.School
import services.CrudService
import services.institutions.Impl.SchoolServiceImpl

trait SchoolService extends CrudService[School]{

}

object SchoolService{
  def apply: SchoolService = new SchoolServiceImpl()
}
