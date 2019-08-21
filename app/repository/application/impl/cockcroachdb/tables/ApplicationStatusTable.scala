package repository.application.impl.cockcroachdb.tables

import java.time.LocalDateTime

import akka.http.javadsl.model.DateTime
import domain.application.ApplicationStatus
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationStatusTable(tag: Tag) extends Table[ApplicationStatus] (tag, _tableName = "application_status") {

  def applicationStatusId: Rep[String] = column[String]("application_status_id", O.PrimaryKey)

  def description: Rep[String] = column[String]("description")

  def date: Rep[LocalDateTime] = column[LocalDateTime]("date")

  override def * : ProvenShape[ApplicationStatus] = (applicationStatusId,description,date) <> ((ApplicationStatus.apply _).tupled, ApplicationStatus.unapply)
}

object ApplicationStatusTable extends TableQuery(new ApplicationStatusTable(_)){
  def db: driver.api.Database =PgDBConnection.db
  
  def getEntity(applicationStatusId:String):Future[Option[ApplicationStatus]] ={
    db.run(this.filter(_.applicationStatusId === applicationStatusId).result).map(_.headOption)
  }

  def saveEntity(applicationStatus: ApplicationStatus): Future[Option[ApplicationStatus]] = {
    db.run(
      (this returning this).insertOrUpdate(applicationStatus)
    )
  }

  def getEntities: Future[Seq[ApplicationStatus]] = {
    db.run(ApplicationStatusTable.result)
  }

  def deleteEntity(applicationStatusId: String): Future[Int] = {
    db.run(this.filter(_.applicationStatusId === applicationStatusId).delete)
  }

  def createTable = {
    db.run(
      ApplicationStatusTable.schema.createIfNotExists
    ).isCompleted
  }
  
}