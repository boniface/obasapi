package repository.application.impl.cockcroachdb.tables

import domain.application.Application
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationTable (tag: Tag) extends Table[Application](tag, _tableName = "application"){

  def id: Rep[String] = column[String]("id", O.PrimaryKey)

  def applicationTypeId: Rep[String] = column[String]("application_type_id")

  def applicantTypeId: Rep[String] = column[String]("applicant_type_id")

  override def * : ProvenShape[Application] = (id, applicationTypeId, applicantTypeId) <> ((Application.apply _).tupled, Application.unapply)
}

object ApplicationTable extends TableQuery(new ApplicationTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[Application]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(application: Application): Future[Option[Application]] = {
    db.run(
      (this returning this).insertOrUpdate(application)
    )
  }

  def getEntities: Future[Seq[Application]] = {
    db.run(ApplicationTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      ApplicationTable.schema.createIfNotExists
    ).isCompleted
  }

}
