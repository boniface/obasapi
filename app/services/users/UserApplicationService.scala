package services.users

import domain.users.UserApplication
import services.CrudService
import services.users.Impl.UserApplicationServiceImpl
import scala.concurrent.Future

trait UserApplicationService extends CrudService[UserApplication]{

  def getEntity(userId: String, addressTypeId: String): Future[Option[UserApplication]]

  def getEntityForUser(userId: String): Future[Seq[UserApplication]]

}

object UserApplicationService{
  def roach: UserApplicationService = new UserApplicationServiceImpl()
}
