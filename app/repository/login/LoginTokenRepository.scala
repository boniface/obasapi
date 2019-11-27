package repository.login

import domain.login.LoginToken
import repository.Repository


trait LoginTokenRepository extends Repository[LoginToken] {

}

object LoginTokenRepository {
  def apply: LoginTokenRepository = new LoginTokenRepositoryImpl()
}

