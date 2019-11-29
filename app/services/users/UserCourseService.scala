package services.users

import domain.users.UserCourse
import services.CrudService
import services.users.Impl.UserCourseServiceImpl

import scala.concurrent.Future

trait UserCourseService extends CrudService[UserCourse] {
  def getEntity(userId: String, institutionId: String, courseId: String): Future[Option[UserCourse]]
  def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserCourse]]
  def getEntitiesForUser(userId: String): Future[Seq[UserCourse]]
}

object UserCourseService {
  def apply: UserCourseService = new UserCourseServiceImpl()
}
