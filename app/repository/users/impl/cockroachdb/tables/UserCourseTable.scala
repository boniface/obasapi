package repository.users.impl.cockroachdb.tables

import domain.users.UserCourse
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
class UserCourseTableCreate(tag: Tag) extends Table[UserCourse](tag, "user_course") {

  def userId: Rep[String] = column[String]("user_id")

  def institutionId: Rep[String] = column[String]("institution_id")

  def courseId: Rep[String] = column[String]("course_id")

  def * : ProvenShape[UserCourse] = (userId, institutionId, courseId) <> ((UserCourse.apply _).tupled, UserCourse.unapply)

  def pk = primaryKey("pk_user_course", (userId, institutionId, courseId))
}

object UserCourseTableCreate extends TableQuery(new UserCourseTableCreate(_)) {

  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = {
    db.run(
      UserCourseTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class UserCourseTable(tag: Tag) extends Table[UserCourse](tag, "user_course") {

  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def institutionId: Rep[String] = column[String]("institution_id", O.PrimaryKey)

  def courseId: Rep[String] = column[String]("course_id", O.PrimaryKey)

  def * : ProvenShape[UserCourse] = (userId, institutionId, courseId) <> ((UserCourse.apply _).tupled, UserCourse.unapply)
}

object UserCourseTable extends TableQuery(new UserCourseTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, institutionId: String, courseId: String): Future[Option[UserCourse]] = {
    db.run(
      this.filter(_.userId === userId)
        .filter(_.institutionId === institutionId)
        .filter(_.courseId === courseId).result
    ).map(_.headOption)
  }

  def getEntitiesForUser(userId: String): Future[Seq[UserCourse]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserCourse]] = {
    db.run(this.filter(_.userId === userId).filter(_.institutionId === institutionId).result)
  }

  def saveEntity(userCourse: UserCourse): Future[Option[UserCourse]] = {
    db.run(
      (this returning this).insertOrUpdate(userCourse)
    )
  }

  def getEntities: Future[Seq[UserCourse]] = {
    db.run(UserCourseTable.result)
  }

  def deleteEntity(userId: String, institutionId: String, courseId: String): Future[Int] = {
    db.run(
      this.filter(_.userId === userId)
        .filter(_.institutionId === institutionId)
        .filter(_.courseId === courseId).delete
    )
  }

}
