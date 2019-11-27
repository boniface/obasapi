package repository.academics.tables

import domain.academics.CourseSubject
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * For DDL
 * @param tag
 */
class CourseSubjectTableCreate(tag: Tag) extends Table[CourseSubject](tag, "course_subject"){

  def courseId: Rep[String] = column[String]("course_id")

  def subjectId: Rep[String] = column[String]("subject_id")

  override def * : ProvenShape[CourseSubject] = (courseId, subjectId) <> ((CourseSubject.apply _).tupled, CourseSubject.unapply)

  def pk = primaryKey("pk_course_subject", (courseId, subjectId))
}

object CourseSubjectTableCreate extends TableQuery(new CourseSubjectTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = db.run(CourseSubjectTableCreate.schema.createIfNotExists).isCompleted

}

/**
 * For DML
 * @param tag
 */
class CourseSubjectTable(tag: Tag) extends Table[CourseSubject](tag, "course_subject"){

  def courseId: Rep[String] = column[String]("course_id", O.PrimaryKey)

  def subjectId: Rep[String] = column[String]("subject_id", O.PrimaryKey)

  override def * : ProvenShape[CourseSubject] = (courseId, subjectId) <> ((CourseSubject.apply _).tupled, CourseSubject.unapply)
}

object CourseSubjectTable extends TableQuery(new CourseSubjectTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(courseId: String, subjectId: String): Future[Option[CourseSubject]] = {
    db.run(this.filter(_.courseId === courseId).filter(_.subjectId === subjectId).result).map(_.headOption)
  }

  def getEntitiesForCourse(courseId: String): Future[Seq[CourseSubject]] = {
    db.run(this.filter(_.courseId === courseId).result)
  }

  def saveEntity(courseSubject: CourseSubject): Future[Option[CourseSubject]] = {
    db.run((this returning this).insertOrUpdate(courseSubject))
  }

  def deleteEntity(courseId: String, subjectId: String): Future[Int] =
    db.run(this.filter(_.courseId === courseId).filter(_.subjectId === subjectId).delete)

  def getEntities: Future[Seq[CourseSubject]] = db.run(CourseSubjectTable.result)
}
