package repository.users.impl.cockroachdb.tables

import domain.users.UserApplicationStatus
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver
import java.time.LocalDateTime

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Used for DDL (to create table with composite key)
 * @param tag
 */
class UserApplicationStatusTableCreate(tag: Tag) extends Table[UserApplicationStatus](tag, "user_application_status") {

  def applicationId: Rep[String] = column[String]("application_id")

  def statusId: Rep[String] = column[String]("status_id")

  def modifiedBy: Rep[String] = column[String]("modified_by")

  def comment: Rep[Option[String]] = column[Option[String]]("comment")

  def dateTime: Rep[LocalDateTime] = column[LocalDateTime]("date_time")

  def * : ProvenShape[UserApplicationStatus] = (applicationId, statusId, modifiedBy, comment, dateTime) <> ((UserApplicationStatus.apply _).tupled, UserApplicationStatus.unapply)

  def pk = primaryKey("pk_user_application_status", (applicationId, statusId, dateTime))
}

object UserApplicationStatusTableCreate extends TableQuery(new UserApplicationStatusTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      UserApplicationStatusTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class UserApplicationStatusTable(tag: Tag) extends Table[UserApplicationStatus](tag, "user_application_status") {

  def applicationId: Rep[String] = column[String]("application_id", O.PrimaryKey)

  def statusId: Rep[String] = column[String]("status_id", O.PrimaryKey)

  def modifiedBy: Rep[String] = column[String]("modified_by")

  def comment: Rep[Option[String]] = column[Option[String]]("comment")

  def dateTime: Rep[LocalDateTime] = column[LocalDateTime]("date_time", O.PrimaryKey)

  def * : ProvenShape[UserApplicationStatus] = (applicationId, statusId, modifiedBy, comment, dateTime) <> ((UserApplicationStatus.apply _).tupled, UserApplicationStatus.unapply)
}

object UserApplicationStatusTable extends TableQuery(new UserApplicationStatusTable(_)) {
  type DomainObject = UserApplicationStatus
  def db: driver.api.Database = PgDBConnection.db

  def getEntitiesForAppnStatus(applicationId: String, statusId: String): Future[Seq[DomainObject]] = {
    db.run(this.filter(_.applicationId === applicationId).filter(_.statusId === statusId).result)
  }

  def getLatestForAppnStatus(applicationId: String, statusId: String): Future[Option[DomainObject]] = {
    db.run(this.filter(_.applicationId === applicationId).filter(_.statusId === statusId).result)
      .map(_.sorted(UserApplicationStatus.orderByDateTime)).map(_.headOption)
  }

  def getEntitiesForApplication(applicationId: String): Future[Seq[DomainObject]] = {
    db.run(this.filter(_.applicationId === applicationId).result)
  }

  def getLatestForApplication(applicationId: String): Future[Option[DomainObject]] = {
    db.run(this.filter(_.applicationId === applicationId).result)
      .map(_.sorted(UserApplicationStatus.orderByDateTime)).map(_.headOption)
  }

  def saveEntity(userAddress: UserApplicationStatus): Future[Option[DomainObject]] = {
    db.run(
      (this returning this).insertOrUpdate(userAddress)
    )
  }

  def getEntities: Future[Seq[DomainObject]] = {
    db.run(UserApplicationStatusTable.result)
  }

}
