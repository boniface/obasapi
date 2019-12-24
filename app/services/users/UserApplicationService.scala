package services.users

import domain.users.UserApplication
import services.CrudService
import services.users.Impl.UserApplicationServiceImpl
import scala.concurrent.Future

trait UserApplicationService extends CrudService[UserApplication]{
  def getEntity(id: String, applicationId: String): Future[Option[UserApplication]]
  def getEntitiesForUser(id: String): Future[Seq[UserApplication]]
  def getLatestEntityForUser(userId: String): Future[Option[UserApplication]]
  def getEntityForApplication(applicationId: String): Future[Option[UserApplication]]
}

object UserApplicationService{
  def roach: UserApplicationService = new UserApplicationServiceImpl()
}
