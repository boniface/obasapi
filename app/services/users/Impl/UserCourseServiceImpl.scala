package services.users.Impl

import domain.users.UserCourse
import repository.users.UserCourseRepository
import services.users.UserCourseService

import scala.concurrent.Future

class UserCourseServiceImpl extends UserCourseService {
  override def getEntity(userId: String, institutionId: String, courseId: String): Future[Option[UserCourse]] =
    UserCourseRepository.apply.getEntity(userId, institutionId, courseId)

  override def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserCourse]] =
    UserCourseRepository.apply.getEntitiesForUserPerInstitution(userId, institutionId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserCourse]] =
    UserCourseRepository.apply.getEntitiesForUser(userId)

  override def saveEntity(entity: UserCourse): Future[Option[UserCourse]] =
    UserCourseRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[UserCourse]] = UserCourseRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[UserCourse]] = ???

  override def deleteEntity(entity: UserCourse): Future[Boolean] =
    UserCourseRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserCourseRepository.apply.createTable
}
