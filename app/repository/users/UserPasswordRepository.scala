package repository.users

import domain.users.UserPassword
import repository.Repository
import repository.users.Impl.cockroachdb

trait UserPasswordRepository extends Repository[UserPassword]{

}

object UserPasswordRepository{
  def roach: UserPasswordRepository = new cockroachdb.UserPasswordRepositoryImpl()
}
