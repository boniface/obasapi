package repository.institutions

import domain.institutions.Institution
import repository.Repository
import repository.institutions.impl.cockroachdb.InstitutionRepositoryImpl

trait InstitutionRepository extends Repository[Institution]{

}

object InstitutionRepository {
  def apply: InstitutionRepository = new InstitutionRepositoryImpl()
}
