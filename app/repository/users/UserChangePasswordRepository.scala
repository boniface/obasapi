package repository.users

import domain.login.ChangePassword
import repository.Repository
import repository.users.impl.cockroachdb.UserChangePasswordRepositoryImpl

trait UserChangePasswordRepository extends Repository[ChangePassword]{

}

object UserChangePasswordRepository {
  def apply: UserChangePasswordRepository = new UserChangePasswordRepositoryImpl()
}
