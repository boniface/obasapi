package repository.institutions.impl.cockroachdb.tables

import domain.institutions.InstitutionCourse
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
class InstitutionCourseTableCreate(tag: Tag) extends Table[InstitutionCourse](tag, "institution_course") {
  def institutionId: Rep[String] = column[String]("institution_id")

  def courseId: Rep[String] = column[String]("course_id")

  def * : ProvenShape[InstitutionCourse] = (institutionId, courseId) <> ((InstitutionCourse.apply _).tupled, InstitutionCourse.unapply)

  def pk = primaryKey("pk_institution_course", (institutionId, courseId))
}

object InstitutionCourseTableCreate extends TableQuery(new InstitutionCourseTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      InstitutionCourseTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class InstitutionCourseTable(tag: Tag) extends Table[InstitutionCourse](tag, "institution_course") {

  def institutionId: Rep[String] = column[String]("institution_id", O.PrimaryKey)

  def courseId: Rep[String] = column[String]("course_id", O.PrimaryKey)

  def * : ProvenShape[InstitutionCourse] = (institutionId, courseId) <> ((InstitutionCourse.apply _).tupled, InstitutionCourse.unapply)
}

object InstitutionCourseTable extends TableQuery(new InstitutionCourseTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(institutionId: String, courseId: String): Future[Option[InstitutionCourse]] = {
    db.run(this.filter(_.institutionId === institutionId).filter(_.courseId === courseId).result).map(_.headOption)
  }

  def getEntitiesForInstitution(institutionId: String): Future[Seq[InstitutionCourse]] = {
    db.run(this.filter(_.institutionId === institutionId).result)
  }

  def saveEntity(institutionCourse: InstitutionCourse): Future[Option[InstitutionCourse]] = {
    db.run(
      (this returning this).insertOrUpdate(institutionCourse)
    )
  }

  def getEntities: Future[Seq[InstitutionCourse]] = {
    db.run(InstitutionCourseTable.result)
  }

  def deleteEntity(institutionId: String, courseId: String): Future[Int] = {
    db.run(this.filter(_.institutionId === institutionId).filter(_.courseId === courseId).delete)
  }

}