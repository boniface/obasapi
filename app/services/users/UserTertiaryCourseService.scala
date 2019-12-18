package services.users

import domain.users.UserTertiaryCourse
import services.CrudService
import services.users.Impl.UserTertiaryCourseServiceImpl

import scala.concurrent.Future

trait UserTertiaryCourseService extends CrudService[UserTertiaryCourse] {
  def getEntity(userId: String, applicationId: String): Future[Option[UserTertiaryCourse]]
  def getEntitiesForUser(userId: String): Future[Seq[UserTertiaryCourse]]
}

object UserTertiaryCourseService {
  def apply: UserTertiaryCourseService = new UserTertiaryCourseServiceImpl()
}
