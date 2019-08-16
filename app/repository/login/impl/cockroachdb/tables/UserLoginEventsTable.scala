package repository.login.impl.cockroachdb.tables

import java.time.LocalDateTime

import domain.login.UserLoginEvents
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserLoginEventsTable(tag: Tag) extends Table[UserLoginEvents](tag, "USERS_LOGIN_EVENTS") {
  def id: Rep[String] = column[String]("USEREVENTS_ID", O.PrimaryKey)

  def email: Rep[String] = column[String]("EMAIL")

  def date: Rep[LocalDateTime] = column[LocalDateTime]("DATETIME")

  def description: Rep[String] = column[String]("DESCRIPTION")

  def * : ProvenShape[UserLoginEvents] = (id,email, date, description) <> ((UserLoginEvents.apply _).tupled, UserLoginEvents.unapply)
}

object UserLoginEventsTable extends TableQuery(new UserLoginEventsTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[UserLoginEvents]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(userLoginEvents: UserLoginEvents): Future[Option[UserLoginEvents]] = {
    db.run(
      (this returning this).insertOrUpdate(userLoginEvents)
    )
  }

  def getEntities: Future[Seq[UserLoginEvents]] = {
    db.run(UserLoginEventsTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      UserLoginEventsTable.schema.createIfNotExists
    ).isCompleted
  }


}