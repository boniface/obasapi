package repository.users

import domain.users.UserInstitution
import repository.Repository
import repository.users.Impl.cockroachdb

trait UserInstitutionRepository extends Repository[UserInstitution]{

}

object UserInstitutionRepository{
  def roach: UserInstitutionRepository = new cockroachdb.UserInstitutionRepositoryImpl()
}
