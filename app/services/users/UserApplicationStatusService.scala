package services.users

import domain.users.UserApplicationStatus
import services.CrudService
import services.users.Impl.UserApplicationStatusServiceImpl

import scala.concurrent.Future

trait UserApplicationStatusService extends CrudService[UserApplicationStatus] {
  def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[UserApplicationStatus]]
  def getEntitiesForApplication(applicationId: String): Future[Seq[UserApplicationStatus]]
  def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[UserApplicationStatus]]
  def getLatestForApplication(applicationId: String): Future[Option[UserApplicationStatus]]
}

object UserApplicationStatusService{
  def roach: UserApplicationStatusService = new UserApplicationStatusServiceImpl()
}
