package services.users

import domain.users.UserPassword
import services.CrudService
import services.users.Impl.cockroachdb

trait UserPasswordService extends CrudService[UserPassword]{

}

object UserPasswordService{
  def roach: UserPasswordService = new cockroachdb.UserPasswordServiceImpl()
}
