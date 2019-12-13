package repository.institutions

import domain.institutions.InstitutionLocation
import repository.Repository
import repository.institutions.impl.cockroachdb.InstitutionLocationRepositoryImpl

import scala.concurrent.Future

trait InstitutionLocationRepository extends Repository[InstitutionLocation] {
  def getEntitiesForLocation(locationId: String): Future[Seq[InstitutionLocation]]
}

object InstitutionLocationRepository {
  def apply: InstitutionLocationRepository = new InstitutionLocationRepositoryImpl()
}
