package repository.users.impl.cockroachdb

import domain.users.UserTertiaryCourse
import repository.users.UserTertiaryCourseRepository
import repository.users.impl.cockroachdb.tables.{UserTertiaryCourseTable, UserTertiaryCourseTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserTertiaryCourseRepositoryImpl extends UserTertiaryCourseRepository {
  override def getEntity(userId: String, applicationId: String): Future[Option[UserTertiaryCourse]] =
    UserTertiaryCourseTable.getEntity(userId, applicationId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserTertiaryCourse]] =
    UserTertiaryCourseTable.getEntitiesForUser(userId)

  override def saveEntity(entity: UserTertiaryCourse): Future[Option[UserTertiaryCourse]] =
    UserTertiaryCourseTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserTertiaryCourse]] =
    UserTertiaryCourseTable.getEntities

  override def getEntity(id: String): Future[Option[UserTertiaryCourse]] = ???

  override def deleteEntity(entity: UserTertiaryCourse): Future[Boolean] =
    UserTertiaryCourseTable.deleteEntity(entity.userId, entity.applicationId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(UserTertiaryCourseTableCreate.createTable)
}
