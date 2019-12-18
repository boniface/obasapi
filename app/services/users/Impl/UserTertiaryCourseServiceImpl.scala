package services.users.Impl

import domain.users.UserTertiaryCourse
import repository.users.UserTertiaryCourseRepository
import services.users.UserTertiaryCourseService

import scala.concurrent.Future

class UserTertiaryCourseServiceImpl extends UserTertiaryCourseService {
  override def getEntity(userId: String, applicationId: String): Future[Option[UserTertiaryCourse]] =
    UserTertiaryCourseRepository.apply.getEntity(userId, applicationId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserTertiaryCourse]] =
    UserTertiaryCourseRepository.apply.getEntitiesForUser(userId)

  override def saveEntity(entity: UserTertiaryCourse): Future[Option[UserTertiaryCourse]] =
    UserTertiaryCourseRepository.apply.saveEntity(entity)

  override def getEntities: Future[Seq[UserTertiaryCourse]] =
    UserTertiaryCourseRepository.apply.getEntities

  override def getEntity(id: String): Future[Option[UserTertiaryCourse]] = ???

  override def deleteEntity(entity: UserTertiaryCourse): Future[Boolean] =
    UserTertiaryCourseRepository.apply.deleteEntity(entity)

  override def createTable: Future[Boolean] = UserTertiaryCourseRepository.apply.createTable
}
