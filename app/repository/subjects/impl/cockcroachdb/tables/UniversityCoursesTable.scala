package repository.subjects.impl.cockcroachdb.tables

import domain.subjects.UniversityCourses
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UniversityCoursesTable(tag: Tag) extends Table[UniversityCourses](tag,_tableName = "UNIVERSITY_COURSES"){

  def courseCode: Rep[String] = column[String]("COURSE_CODE", O.PrimaryKey)

  def description: Rep[Option[String]] = column[Option[String]]("DESCRIPTION")

  def name: Rep[String] = column[String]("NAME")

  def Type: Rep[String] = column[String]("TYPE")

  def Term: Rep[String] = column[String]("TERM")

  def * : ProvenShape[UniversityCourses] = (courseCode, description, name,Type,Term) <> ((UniversityCourses.apply _).tupled, UniversityCourses.unapply)
}

object UniversityCoursesTable extends TableQuery(new UniversityCoursesTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(courseCode: String): Future[Option[UniversityCourses]] = {
    db.run(this.filter(_.courseCode === courseCode).result).map(_.headOption)
  }

  def saveEntity(universitycourses: UniversityCourses): Future[UniversityCourses] = {
    db.run(this returning this.map(_.courseCode) into ((acc, courseCode) => acc.copy(courseCode = courseCode)) += universitycourses)
  }

  def getEntities: Future[Seq[UniversityCourses]] = {
    db.run(UniversityCoursesTable.result)
  }

  def deleteEntity(courseCode: String): Future[Int] = {
    db.run(this.filter(_.courseCode === courseCode).delete)
  }

  def createTable = {
    db.run(
      UniversityCoursesTable.schema.createIfNotExists
    ).isCompleted
  }

}