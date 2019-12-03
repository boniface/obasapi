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

  def applicationId: Rep[String] = column[String]("user_id")

  def statusId: Rep[String] = column[String]("address_type_id")

  def modifiedBy: Rep[String] = column[String]("modifiedBy")

  def dateTime: Rep[LocalDateTime] = column[LocalDateTime]("date_time")

  def * : ProvenShape[UserApplicationStatus] = (applicationId, statusId, modifiedBy, dateTime) <> ((UserApplicationStatus.apply _).tupled, UserApplicationStatus.unapply)

  def pk = primaryKey("pk_user_address", (applicationId, statusId))
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

  def modifiedBy: Rep[String] = column[String]("modifiedBy")

  def dateTime: Rep[LocalDateTime] = column[LocalDateTime]("date_time")

  def * : ProvenShape[UserApplicationStatus] = (applicationId, statusId, modifiedBy, dateTime) <> ((UserApplicationStatus.apply _).tupled, UserApplicationStatus.unapply)
}

object UserApplicationStatusTable extends TableQuery(new UserApplicationStatusTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(applicationId: String, statusId: String): Future[Option[UserApplicationStatus]] = {
    db.run(this.filter(_.applicationId === applicationId).filter(_.statusId === statusId).result).map(_.headOption)
  }

  def getEntityForApplication(applicationId: String): Future[Seq[UserApplicationStatus]] = {
    db.run(this.filter(_.applicationId === applicationId).result)
  }

  def saveEntity(userAddress: UserApplicationStatus): Future[Option[UserApplicationStatus]] = {
    db.run(
      (this returning this).insertOrUpdate(userAddress)
    )
  }

  def getEntities: Future[Seq[UserApplicationStatus]] = {
    db.run(UserApplicationStatusTable.result)
  }

  def deleteEntity(applicationId: String, statusId: String): Future[Int] = {
    db.run(this.filter(_.applicationId === applicationId).filter(_.statusId === statusId).delete)
  }

}
