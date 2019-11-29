package repository.application.impl.cockcroachdb.tables

import domain.application.ApplicationStatus
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationStatusTable(tag: Tag) extends Table[ApplicationStatus] (tag, _tableName = "application_status") {

  def id: Rep[String] = column[String]("id", O.PrimaryKey)

  def name: Rep[String] = column[String]("name")

  def description: Rep[Option[String]] = column[Option[String]]("description")

  override def * : ProvenShape[ApplicationStatus] = (id, name, description) <> ((ApplicationStatus.apply _).tupled, ApplicationStatus.unapply)
}

object ApplicationStatusTable extends TableQuery(new ApplicationStatusTable(_)){
  def db: driver.api.Database =PgDBConnection.db

  def getEntity(id:String):Future[Option[ApplicationStatus]] ={
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(applicationStatus: ApplicationStatus): Future[Option[ApplicationStatus]] = {
    db.run(
      (this returning this).insertOrUpdate(applicationStatus)
    )
  }

  def getEntities: Future[Seq[ApplicationStatus]] = {
    db.run(ApplicationStatusTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      ApplicationStatusTable.schema.createIfNotExists
    ).isCompleted
  }
  
}