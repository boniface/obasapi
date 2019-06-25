package repository.users

import domain.users.UserRelative
import repository.Repository
import repository.users.Impl.cassandra.UserRelativeRepositoryImpl

trait UserRelativeRepository extends Repository[UserRelative]{

}

object UserRelativeRepository{
  def apply: UserRelativeRepository = new UserRelativeRepositoryImpl()
}


