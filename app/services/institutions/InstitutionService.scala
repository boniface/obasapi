package services.institutions

import domain.institutions.Institution
import services.CrudService
import services.institutions.Impl.cockroachdb.InstitutionServiceImpl

trait InstitutionService extends CrudService[Institution] {

}

object InstitutionService {
  def apply: InstitutionService = new InstitutionServiceImpl()
}
