package repository.users

import domain.users.UserMatricInstitution
import repository.Repository
import repository.users.impl.cockroachdb.UserMatricInstitutionRepositoryImpl

trait UserMatricInstitutionRepository extends Repository[UserMatricInstitution] {

}

object UserMatricInstitutionRepository {
  def apply: UserMatricInstitutionRepository = new UserMatricInstitutionRepositoryImpl()
}
