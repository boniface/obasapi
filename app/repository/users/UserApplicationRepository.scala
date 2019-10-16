package repository.users

import domain.users.UserApplication
import repository.Repository
import repository.users.impl.cockroachdb.UserApplicationRepositoryImpl

trait UserApplicationRepository extends Repository[UserApplication]{

}

object UserApplicationRepository{
  def roach: UserApplicationRepository = new UserApplicationRepositoryImpl()
}
