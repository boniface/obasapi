package repository.institutions

import domain.institutions.InstitutionLocation
import repository.Repository
import repository.institutions.impl.cockroachdb.InstitutionLocationRepositoryImpl

trait InstitutionLocationRepository extends Repository[InstitutionLocation] {

}

object InstitutionLocationRepository {
  def apply: InstitutionLocationRepository = new InstitutionLocationRepositoryImpl()
}
