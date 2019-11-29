package repository.users.impl.cockroachdb

import domain.users.UserCourse
import repository.users.UserCourseRepository
import repository.users.impl.cockroachdb.tables.{UserCourseTable, UserCourseTableCreate}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * @author Arinze
 */
class UserCourseRepositoryImpl extends UserCourseRepository {
  override def getEntity(userId: String, institutionId: String, courseId: String): Future[Option[UserCourse]] =
    UserCourseTable.getEntity(userId, institutionId, courseId)

  override def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserCourse]] =
    UserCourseTable.getEntitiesForUserPerInstitution(userId, institutionId)

  override def getEntitiesForUser(userId: String): Future[Seq[UserCourse]] =
    UserCourseTable.getEntitiesForUser(userId)

  override def saveEntity(entity: UserCourse): Future[Option[UserCourse]] =
    UserCourseTable.saveEntity(entity)

  override def getEntities: Future[Seq[UserCourse]] = UserCourseTable.getEntities

  override def getEntity(id: String): Future[Option[UserCourse]] = ???

  override def deleteEntity(entity: UserCourse): Future[Boolean] =
    UserCourseTable.deleteEntity(entity.userId, entity.institutionId, entity.courseId).map(value => value.isValidInt)

  override def createTable: Future[Boolean] =
    Future.successful(UserCourseTableCreate.createTable)
}
