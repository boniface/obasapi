package repository.users.impl.cockroachdb.tables

import domain.users.UserApplicationCourse
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
class UserApplicationCourseTableCreate(tag: Tag) extends Table[UserApplicationCourse](tag, "user_application_course") {
  def userId: Rep[String] = column[String]("user_id")

  def applicationId: Rep[String] = column[String]("application_id")

  def courseId: Rep[String] = column[String]("course_id")

  def * : ProvenShape[UserApplicationCourse] = (userId, applicationId, courseId) <> ((UserApplicationCourse.apply _).tupled, UserApplicationCourse.unapply)

  def pk = primaryKey("pk_user_application_course", (userId, applicationId))
}

object UserApplicationCourseTableCreate extends TableQuery(new UserApplicationCourseTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = {
    db.run(
      UserApplicationCourseTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class UserApplicationCourseTable(tag: Tag) extends Table[UserApplicationCourse](tag, "user_application_course") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def applicationId: Rep[String] = column[String]("application_id", O.PrimaryKey)

  def courseId: Rep[String] = column[String]("course_id")

  def * : ProvenShape[UserApplicationCourse] = (userId, applicationId, courseId) <> ((UserApplicationCourse.apply _).tupled, UserApplicationCourse.unapply)
}

object UserApplicationCourseTable extends TableQuery(new UserApplicationCourseTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationCourse]] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).result).map(_.headOption)
  }

  def getEntitiesForUser(userId: String): Future[Seq[UserApplicationCourse]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def saveEntity(userApplicationCourse: UserApplicationCourse): Future[Option[UserApplicationCourse]] = {
    db.run(
      (this returning this).insertOrUpdate(userApplicationCourse)
    )
  }

  def getEntities: Future[Seq[UserApplicationCourse]] = {
    db.run(UserApplicationCourseTable.result)
  }

  def deleteEntity(userId: String, applicationId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).delete)
  }

}
