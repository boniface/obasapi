package repository.academics.impl.cockcroachdb.tables

import domain.academics.Course
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CourseTable(tag: Tag) extends Table[Course](tag, "course"){

  def id: Rep[String] = column[String]("id", O.PrimaryKey)

  def courseName: Rep[String] = column[String]("course_name")

  def courseDesc: Rep[Option[String]] = column[Option[String]]("course_description")

  override def * : ProvenShape[Course] = (id, courseName, courseDesc) <> ((Course.apply _).tupled, Course.unapply)
}

object CourseTable extends TableQuery(new CourseTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[Course]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(course: Course): Future[Option[Course]] = {
    db.run(
      (this returning this).insertOrUpdate(course)
    )
  }

  def getEntities: Future[Seq[Course]] = {
    db.run(CourseTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      CourseTable.schema.createIfNotExists
    ).isCompleted
  }

}
