package repository.academics.impl.cockcroachdb.tables

import domain.academics.Subject
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class SubjectTable(tag: Tag) extends Table[Subject](tag, "subject") {

  def id: Rep[String] = column[String]("id", O.PrimaryKey)

  def subjectName: Rep[String] = column[String]("subject_name")

  def subjectDesc: Rep[Option[String]] = column[Option[String]]("subject_description")

  override def * : ProvenShape[Subject] = (id, subjectName, subjectDesc) <> ((Subject.apply _).tupled, Subject.unapply)
}

object SubjectTable extends TableQuery(new SubjectTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[Subject]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(subject: Subject): Future[Option[Subject]] = {
    db.run(
      (this returning this).insertOrUpdate(subject)
    )
  }

  def getEntities: Future[Seq[Subject]] = {
    db.run(SubjectTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      SubjectTable.schema.createIfNotExists
    ).isCompleted
  }
}
