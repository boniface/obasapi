package services.institutions

import domain.institutions.InstitutionType
import services.CrudService
import services.institutions.Impl.cockroachdb.InstitutionTypeServiceImpl

trait InstitutionTypeService extends CrudService[InstitutionType] {

}

object InstitutionTypeService {
  def apply: InstitutionTypeService = new InstitutionTypeServiceImpl()
}
