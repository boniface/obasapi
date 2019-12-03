package services.users

import domain.users.UserApplicationStatus
import services.CrudService
import services.users.Impl.UserApplicationStatusServiceImpl

import scala.concurrent.Future

trait UserApplicationStatusService extends CrudService[UserApplicationStatus] {

  def getEntity(applicationId: String, statusId: String): Future[Option[UserApplicationStatus]]

  def getEntityForApplication(userId: String): Future[Seq[UserApplicationStatus]]

}

object UserApplicationStatusService{
  def roach: UserApplicationStatusService = new UserApplicationStatusServiceImpl()
}
