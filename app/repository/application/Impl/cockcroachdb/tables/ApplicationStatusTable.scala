package repository.application.Impl.cockcroachdb.tables

import java.time.LocalDateTime

import akka.http.javadsl.model.DateTime
import domain.application.ApplicationStatus
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationStatusTable(tag: Tag) extends Table[ApplicationStatus] (tag, _tableName = "APPLICATION_STATUS") {

  def applicationStatusId: Rep[String] = column[String]("APPLICATION_ID", O.PrimaryKey)

  def description: Rep[String] = column[String]("DESCRIPTION")

  def date: Rep[LocalDateTime] = column[LocalDateTime]("DATETIME")

  override def * : ProvenShape[ApplicationStatus] = (applicationStatusId,description,date) <> ((ApplicationStatus.apply _).tupled, ApplicationStatus.unapply)
}

object ApplicationStatusTable extends TableQuery(new ApplicationStatusTable(_)){
  def db: driver.api.Database =PgDBConnection.db
  
  def getEntity(applicationStatusId:String):Future[Option[ApplicationStatus]] ={
    db.run(this.filter(_.applicationStatusId === applicationStatusId).result).map(_.headOption)
  }

  def saveEntity(applicationStatus: ApplicationStatus): Future[ApplicationStatus] = {
    db.run(this returning this.map(_.applicationStatusId) into ((acc, applicationStatusId) => acc.copy(applicationStatusId = applicationStatusId)) += applicationStatus)
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