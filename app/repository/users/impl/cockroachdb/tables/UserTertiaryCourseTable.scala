package repository.users.impl.cockroachdb.tables

import domain.users.UserTertiaryCourse
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Used for DDL (to create table with composite key)
 * @param tag
 */
class UserTertiaryCourseTableCreate(tag: Tag) extends Table[UserTertiaryCourse](tag, "user_tertiary_course") {
  def userId: Rep[String] = column[String]("user_id")

  def applicationId: Rep[String] = column[String]("application_id")

  def courseId: Rep[String] = column[String]("course_id")

  def * : ProvenShape[UserTertiaryCourse] = (userId, applicationId, courseId) <> ((UserTertiaryCourse.apply _).tupled, UserTertiaryCourse.unapply)

  def pk = primaryKey("pk_user_tertiary_course", (userId, applicationId))
}

object UserTertiaryCourseTableCreate extends TableQuery(new UserTertiaryCourseTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = {
    db.run(
      UserTertiaryCourseTableCreate.schema.createIfNotExists
    ).isCompleted
  }

}

/**
 * Used for DML
 * @param tag
 */
class UserTertiaryCourseTable(tag: Tag) extends Table[UserTertiaryCourse](tag, "user_tertiary_course") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def applicationId: Rep[String] = column[String]("application_id", O.PrimaryKey)

  def courseId: Rep[String] = column[String]("course_id")

  def * : ProvenShape[UserTertiaryCourse] = (userId, applicationId, courseId) <> ((UserTertiaryCourse.apply _).tupled, UserTertiaryCourse.unapply)
}

object UserTertiaryCourseTable extends TableQuery(new UserTertiaryCourseTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, applicationId: String): Future[Option[UserTertiaryCourse]] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).result).map(_.headOption)
  }

  def getEntitiesForUser(userId: String): Future[Seq[UserTertiaryCourse]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def saveEntity(userTertiaryCourse: UserTertiaryCourse): Future[Option[UserTertiaryCourse]] = {
    db.run(
      (this returning this).insertOrUpdate(userTertiaryCourse)
    )
  }

  def getEntities: Future[Seq[UserTertiaryCourse]] = {
    db.run(UserTertiaryCourseTable.result)
  }

  def deleteEntity(userId: String, applicationId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).delete)
  }

}

