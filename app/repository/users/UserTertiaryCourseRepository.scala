package repository.users

import domain.users.UserTertiaryCourse
import repository.Repository
import repository.users.impl.cockroachdb.UserTertiaryCourseRepositoryImpl

import scala.concurrent.Future

trait UserTertiaryCourseRepository extends Repository[UserTertiaryCourse] {
  def getEntity(userId: String, applicationId: String): Future[Option[UserTertiaryCourse]]
  def getEntitiesForUser(userId: String): Future[Seq[UserTertiaryCourse]]
}

object UserTertiaryCourseRepository {
  def apply: UserTertiaryCourseRepository = new UserTertiaryCourseRepositoryImpl()
}
