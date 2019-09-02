package repository.users

import domain.users.UserRelative
import repository.Repository
import repository.users.impl.cockroachdb.UserRelativeRepositoryImpl

trait UserRelativeRepository extends Repository[UserRelative]{

}

object UserRelativeRepository{
  def roach: UserRelativeRepository = new UserRelativeRepositoryImpl()
}


