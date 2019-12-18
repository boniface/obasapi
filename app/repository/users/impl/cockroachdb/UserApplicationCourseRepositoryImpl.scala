package repository.users.impl.cockroachdb

import domain.users.UserApplicationCourse
import repository.users.UserApplicationCourseRepository
import repository.users.impl.cockroachdb.tables.{UserApplicationCourseTable, UserApplicationCourseTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserApplicationCourseRepositoryImpl extends UserApplicationCourseRepository {
  override def saveEntity(entity: UserApplicationCourse): Future[Option[UserApplicationCourse]] =
    UserApplicationCourseTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserApplicationCourse]] =
    UserApplicationCourseTable.getEntities

  override def getEntity(id: String): Future[Option[UserApplicationCourse]] = ???

  override def deleteEntity(entity: UserApplicationCourse): Future[Boolean] =
    UserApplicationCourseTable.deleteEntity(entity.userId, entity.applicationId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(UserApplicationCourseTableCreate.createTable)

  override def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationCourse]] =
    UserApplicationCourseTable.getEntity(userId, applicationId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserApplicationCourse]] =
    UserApplicationCourseTable.getEntitiesForUser(userId)
}
