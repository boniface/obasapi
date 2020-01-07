package services.institutions

import domain.institutions.InstitutionLocation
import services.CrudService
import services.institutions.Impl.cockroachdb.InstitutionLocationServiceImpl

import scala.concurrent.Future

trait InstitutionLocationService extends CrudService[InstitutionLocation] {
  def getEntitiesForLocation(locationId: String): Future[Seq[InstitutionLocation]]
}

object InstitutionLocationService {
  def apply: InstitutionLocationService = new InstitutionLocationServiceImpl()
}
