package repository.log.Impl.cockroachdb.tables

import domain.log.LogEvent
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import java.time.LocalDateTime
import java.util.UUID

import play.api.libs.json.Json
import util.HelperUtil

import scala.concurrent.Future


class LogEventTable(tag: Tag) extends Table[LogEvent](tag, "log_event") {
  def id: Rep[String] = column[String]("id", O.PrimaryKey)

  def eventName: Rep[String] = column[String]("event_name")

  def eventType: Rep[String] = column[String]("event_type")

  def message: Rep[String] = column[String]("message")

  def date: Rep[LocalDateTime] = column[LocalDateTime]("datetime")



  def * : ProvenShape[LogEvent] = (id,eventName, eventType, message,date) <> ((LogEvent.apply _).tupled, LogEvent.unapply)
}

object LogEventTable extends TableQuery(new LogEventTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[LogEvent]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(logevent: LogEvent): Future[Option[LogEvent]] = {
    db.run(
      (this returning this).insertOrUpdate(logevent)
    )
  }

  def getEntities: Future[Seq[LogEvent]] = {
    db.run(LogEventTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      LogEventTable.schema.createIfNotExists
    ).isCompleted
  }

}