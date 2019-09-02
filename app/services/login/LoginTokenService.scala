package services.login

import domain.login.LoginToken
import services.CrudService
import services.login.Impl.LoginTokenServiceImpl

trait LoginTokenService extends CrudService[LoginToken]{

  def isTokenValid(token: String): Either[Throwable,Boolean]

  def getUserEmail(token: String): String

  def getUserRole(token: String): String

}

object LoginTokenService{
  def apply: LoginTokenService = new LoginTokenServiceImpl()
}

