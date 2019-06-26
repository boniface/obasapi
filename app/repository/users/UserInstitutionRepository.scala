package repository.users

import domain.users.UserInstitution
import repository.Repository
import repository.users.Impl.cassandra.UserInstitutionRepositoryImpl

trait UserInstitutionRepository extends Repository[UserInstitution]{

}

object UserInstitutionRepository{
  def apply: UserInstitutionRepository = new UserInstitutionRepositoryImpl()
}
