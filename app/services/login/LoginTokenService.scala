package services.login

import domain.login.LoginToken
import domain.users.LoginToken
import services.CrudService

trait LoginTokenService extends CrudService[LoginToken]{

  def isTokenValid(token: String): Boolean

  def getUserEmail(token: String): String

  def getUserHashedAgent(token: String): String

  def getUserRole(token: String): String

  def getUserSiteId(token: String): String

  def getUserUserId(token: String): String

}

