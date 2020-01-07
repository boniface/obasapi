package services.users

import domain.users.UserApplicationCourse
import services.CrudService
import services.users.Impl.UserApplicationCourseServiceImpl

import scala.concurrent.Future

trait UserApplicationCourseService extends CrudService[UserApplicationCourse] {
  def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationCourse]]
  def getEntitiesForUser(userId: String): Future[Seq[UserApplicationCourse]]
}

object UserApplicationCourseService {
  def apply: UserApplicationCourseService = new UserApplicationCourseServiceImpl()
}
