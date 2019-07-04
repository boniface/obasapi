package services.users

import domain.users.UserRelative
import services.CrudService
import services.users.Impl.cockroachdb

trait UserRelativeService extends CrudService[UserRelative]{

}

object UserRelativeService{
  def roach: UserRelativeService = new cockroachdb.UserRelativeServiceImpl()
}
