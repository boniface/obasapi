package repository.users

import domain.users.UserPassword
import repository.Repository
import repository.users.impl.cockroachdb.UserPasswordRepositoryImpl

trait UserPasswordRepository extends Repository[UserPassword]{

}

object UserPasswordRepository{
  def roach: UserPasswordRepository = new UserPasswordRepositoryImpl()
}
