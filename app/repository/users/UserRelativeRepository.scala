package repository.users

import domain.users.UserRelative
import repository.Repository
import repository.users.impl.cockroachdb

trait UserRelativeRepository extends Repository[UserRelative]{

}

object UserRelativeRepository{
  def roach: UserRelativeRepository = new cockroachdb.UserRelativeRepositoryImpl()
}


