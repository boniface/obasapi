package services.users.Impl

import domain.users.UserApplicationCourse
import repository.users.UserApplicationCourseRepository
import services.users.UserApplicationCourseService

import scala.concurrent.Future

class UserApplicationCourseServiceImpl extends UserApplicationCourseService {
  override def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationCourse]] =
    UserApplicationCourseRepository.apply.getEntity(userId, applicationId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserApplicationCourse]] =
    UserApplicationCourseRepository.apply.getEntitiesForUser(userId)

  override def saveEntity(entity: UserApplicationCourse): Future[Option[UserApplicationCourse]] =
    UserApplicationCourseRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplicationCourse]] =
    UserApplicationCourseRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[UserApplicationCourse]] = ???

  override def deleteEntity(entity: UserApplicationCourse): Future[Boolean] =
    UserApplicationCourseRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserApplicationCourseRepository.apply.createTable
}
