package repository.institutions.impl.cockroachdb.tables

import domain.institutions.University
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UniversityTable(tag: Tag) extends Table[University](tag, "university") {
  def universityId: Rep[String] = column[String]("university_id", O.PrimaryKey)

  def universityName: Rep[String] = column[String]("university_name")

  def universityProvince: Rep[String] = column[String]("university_province")

  def universityPhoneNumber: Rep[String] = column[String]("university_phone_number")

  def universityEmail: Rep[String] = column [String]("university_email")



  def * : ProvenShape[University] = (universityId, universityName,universityProvince,universityPhoneNumber,universityEmail) <> ((University.apply _).tupled, University.unapply)
}

object UniversityTable extends TableQuery(new UniversityTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(universityId: String): Future[Option[University]] = {
    db.run(this.filter(_.universityId === universityId).result).map(_.headOption)
  }

  def saveEntity(university: University): Future[Option[University]] = {
    db.run(
      (this returning this).insertOrUpdate(university)
    )
  }

  def getEntities: Future[Seq[University]] = {
    db.run(UniversityTable.result)
  }

  def deleteEntity(universityId: String): Future[Int] = {
    db.run(this.filter(_.universityId === universityId).delete)
  }

  def createTable = {
    db.run(
      UniversityTable.schema.createIfNotExists
    ).isCompleted
  }

}