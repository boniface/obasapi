package repository.institutions

import domain.institutions.InstitutionType
import repository.Repository
import repository.institutions.impl.cockroachdb.InstitutionTypeRepositoryImpl

trait InstitutionTypeRepository extends Repository[InstitutionType] {

}

object InstitutionTypeRepository {
  def apply: InstitutionTypeRepository = new InstitutionTypeRepositoryImpl()
}
