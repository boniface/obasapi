package repository.institutions.Impl.cockroachdb.tables

import domain.institutions.University
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UniversityTable(tag: Tag) extends Table[University](tag, "UNIVERSITY") {
  def universityId: Rep[String] = column[String]("UNIVERSITY_ID", O.PrimaryKey)

  def universityName: Rep[String] = column[String]("UNIVERSITY_NAME")

  def universityDetails: Rep[Map[String, String]] = column[Map[String, String]]("UNIVERSITY_DETAILS")

  def universityState: Rep[String] = column[String]("UNIVERSITY_STATE")

  def * : ProvenShape[University] = (universityId, universityName, universityDetails, universityState) <> ((University.apply _).tupled, University.unapply)
}

object UniversityTable extends TableQuery(new UniversityTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(universityId: String): Future[Option[University]] = {
    db.run(this.filter(_.universityId === universityId).result).map(_.headOption)
  }

  def saveEntity(university: University): Future[University] = {
    db.run(this returning this.map(_.universityId) into ((acc, universityId) => acc.copy(universityId = universityId)) += university)
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