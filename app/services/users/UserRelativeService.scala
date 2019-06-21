package services.users

import domain.users.UserRelative
import services.CrudService
import services.users.Impl.UserRelativeServiceImpl

trait UserRelativeService extends CrudService[UserRelative]{

}

object UserRelativeService{
  def apply: UserRelativeService = new UserRelativeServiceImpl()
}
