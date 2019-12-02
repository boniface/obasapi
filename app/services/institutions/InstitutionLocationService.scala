package services.institutions

import domain.institutions.InstitutionLocation
import services.CrudService
import services.institutions.Impl.cockroachdb.InstitutionLocationServiceImpl

trait InstitutionLocationService extends CrudService[InstitutionLocation] {

}

object InstitutionLocationService {
  def apply: InstitutionLocationService = new InstitutionLocationServiceImpl()
}
