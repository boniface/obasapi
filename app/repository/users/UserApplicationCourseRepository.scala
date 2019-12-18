package repository.users

import domain.users.UserApplicationCourse
import repository.Repository
import repository.users.impl.cockroachdb.UserApplicationCourseRepositoryImpl

import scala.concurrent.Future

trait UserApplicationCourseRepository extends Repository[UserApplicationCourse] {
  def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationCourse]]
  def getEntitiesForUser(userId: String): Future[Seq[UserApplicationCourse]]
}

object UserApplicationCourseRepository {
  def apply: UserApplicationCourseRepository = new UserApplicationCourseRepositoryImpl()
}
