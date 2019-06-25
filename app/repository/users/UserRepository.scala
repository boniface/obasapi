package repository.users

import domain.users.User
import repository.Repository
import repository.users.Impl.cassandra.UserRepositoryImpl

trait UserRepository extends Repository[User]{

}

object UserRepository{
  def apply: UserRepository = new UserRepositoryImpl()
}
