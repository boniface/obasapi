package repository.institutions.impl.cockroachdb.tables

import domain.institutions.School
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class SchoolTable(tag: Tag) extends Table[School](tag, "school") {
  def schoolId: Rep[String] = column[String]("school_id", O.PrimaryKey)

  def schoolName: Rep[String] = column[String]("school_name")

  def schoolProvince: Rep[String] = column[String]("school_province")

  def schoolAddress: Rep[String] = column[String]("school_address")

  def schoolPhonenumber: Rep[String] = column[String]("school_phone_number")

  def * : ProvenShape[School] = (schoolId, schoolName, schoolProvince, schoolAddress,schoolPhonenumber) <> ((School.apply _).tupled, School.unapply)
}

object SchoolTable extends TableQuery(new SchoolTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(schoolId: String): Future[Option[School]] = {
    db.run(this.filter(_.schoolId === schoolId).result).map(_.headOption)
  }

  def saveEntity(school: School): Future[Option[School]] = {
    db.run(
      (this returning this).insertOrUpdate(school)
    )
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