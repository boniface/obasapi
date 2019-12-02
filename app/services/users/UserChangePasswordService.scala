package services.users

import domain.login.ChangePassword
import services.CrudService
import services.users.Impl.UserChangePasswordServiceImpl

trait UserChangePasswordService extends CrudService[ChangePassword]{
}

object UserChangePasswordService {
  def apply: UserChangePasswordService = new UserChangePasswordServiceImpl()
}
