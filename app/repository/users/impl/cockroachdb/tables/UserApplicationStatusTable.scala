package repository.users.impl.cockroachdb.tables

import domain.users.UserApplicationStatus
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver
import java.time.LocalDateTime


import scala.concurrent.Future

class UserApplicationStatusTable(tag: Tag) extends Table[UserApplicationStatus](tag, "user_application") {
  def statusId: Rep[String] = column[String]("status_id", O.PrimaryKey)

    def applicationId: Rep[String] = column[String]("application_id")

    def modifiedBy: Rep[String] = column[String]("modified_by")

    def dateTime: Rep[LocalDateTime] = column[LocalDateTime]("date_time")

    def * : ProvenShape[UserApplicationStatus] = (statusId, applicationId, modifiedBy,dateTime) <> ((UserApplicationStatus.apply _).tupled, UserApplicationStatus.unapply)
  }

object UserApplicationStatusTable extends TableQuery(new UserApplicationStatusTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(statusId: String): Future[Option[UserApplicationStatus]] = {
    db.run(this.filter(_.statusId === statusId).result).map(_.headOption)
  }

  def saveEntity(userCommunication: UserApplicationStatus): Future[Option[UserApplicationStatus]] = {
    db.run(
      (this returning this).insertOrUpdate(userCommunication)
    )
  }

  def getEntities: Future[Seq[UserApplicationStatus]] = {
    db.run(UserApplicationStatusTable.result)
  }

  def deleteEntity(statusId: String): Future[Int] = {
    db.run(this.filter(_.statusId === statusId).delete)
  }

  def createTable = {
    db.run(
      UserApplicationStatusTable.schema.createIfNotExists
    ).isCompleted
  }

}

