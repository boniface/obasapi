package repository.users

import domain.users.UserCourse
import repository.Repository
import repository.users.impl.cockroachdb.UserCourseRepositoryImpl

import scala.concurrent.Future

trait UserCourseRepository extends Repository[UserCourse]{
  def getEntity(userId: String, institutionId: String, courseId: String): Future[Option[UserCourse]]
  def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserCourse]]
  def getEntitiesForUser(userId: String): Future[Seq[UserCourse]]
}

object UserCourseRepository {
  def apply: UserCourseRepository = new UserCourseRepositoryImpl()
}
