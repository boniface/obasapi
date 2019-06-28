package repository.subjects.Impl.cockcroachdb.tables

import domain.subjects.MatricSubjects
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MatricSubjectsTable(tag: Tag) extends Table[MatricSubjects](tag, _tableName = "MATRICK_SUBJECTS"){
  
  def subjectCode: Rep[String] = column[String]("SUBJECT_CODE", O.PrimaryKey)

  def description: Rep[Option[String]] = column[Option[String]]("DESCRIPTION")

  def name: Rep[String] = column[String]("NAME")

  def Term: Rep[String] = column[String]("TERM")

  def * : ProvenShape[MatricSubjects] = (subjectCode, description, name,Term) <> ((MatricSubjects.apply _).tupled, MatricSubjects.unapply)
}

object MatricSubjectsTable extends TableQuery(new MatricSubjectsTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(subjectCode: String): Future[Option[MatricSubjects]] = {
    db.run(this.filter(_.subjectCode === subjectCode).result).map(_.headOption)
  }

  def saveEntity(matricSubjects: MatricSubjects): Future[MatricSubjects] = {
    db.run(this returning this.map(_.subjectCode) into ((acc, subjectCode) => acc.copy(subjectCode = subjectCode)) += matricSubjects)
  }

  def getEntities: Future[Seq[MatricSubjects]] = {
    db.run(MatricSubjectsTable.result)
  }

  def deleteEntity(subjectCode: String): Future[Int] = {
    db.run(this.filter(_.subjectCode === subjectCode).delete)
  }

  def createTable = {
    db.run(
      MatricSubjectsTable.schema.createIfNotExists
    ).isCompleted
  }

}