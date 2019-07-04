package repository.institutions.Impl.cockroachdb.tables

import domain.institutions.School
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class SchoolTable(tag: Tag) extends Table[School](tag, "SCHOOL") {
  def schoolId: Rep[String] = column[String]("SCHOOL_ID", O.PrimaryKey)

  def schoolName: Rep[String] = column[String]("SCHOOL_NAME")

  def schoolDetails: Rep[String] = column[String]("SCHOOL_DETAILS")

  def schoolState: Rep[String] = column[String]("SCHOOL_STATE")

  def * : ProvenShape[School] = (schoolId, schoolName, schoolDetails, schoolState) <> ((School.apply _).tupled, School.unapply)
}

object SchoolTable extends TableQuery(new SchoolTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(schoolId: String): Future[Option[School]] = {
    db.run(this.filter(_.schoolId === schoolId).result).map(_.headOption)
  }

  def saveEntity(school: School): Future[School] = {
    db.run(this returning this.map(_.schoolId) into ((acc, schoolId) => acc.copy(schoolId = schoolId)) += school)
  }

  def getEntities: Future[Seq[School]] = {
    db.run(SchoolTable.result)
  }

  def deleteEntity(schoolId: String): Future[Int] = {
    db.run(this.filter(_.schoolId === schoolId).delete)
  }

  def createTable = {
    db.run(
      SchoolTable.schema.createIfNotExists
    ).isCompleted
  }

}