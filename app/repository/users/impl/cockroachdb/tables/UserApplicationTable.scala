package repository.users.impl.cockroachdb.tables

import domain.users.UserApplication
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Used for DDL (to create table with composite key)
 * @param tag
 */
class UserApplicationTableCreate(tag: Tag) extends Table[UserApplication](tag, "user_application") {
  def userId: Rep[String] = column[String]("user_id")

  def applicationId: Rep[String] = column[String]("application_id")

  def * : ProvenShape[UserApplication] = (userId, applicationId) <> ((UserApplication.apply _).tupled, UserApplication.unapply)

  def pk = primaryKey("pk_user_application", (userId, applicationId))
}

object UserApplicationTableCreate extends TableQuery(new UserApplicationTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      UserApplicationTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class UserApplicationTable(tag: Tag) extends Table[UserApplication](tag, "user_application") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def applicationId: Rep[String] = column[String]("application_id", O.PrimaryKey)

  override def * = (userId, applicationId) <> ((UserApplication.apply _).tupled, UserApplication.unapply)
}

object UserApplicationTable extends TableQuery(new UserApplicationTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, applicationId: String): Future[Option[UserApplication]] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).result).map(_.headOption)
  }

  def getEntityForUser(userId: String): Future[Seq[UserApplication]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def saveEntity(userApplication: UserApplication): Future[Option[UserApplication]] = {
    db.run(
      (this returning this).insertOrUpdate(userApplication)
    )
  }

  def getEntities: Future[Seq[UserApplication]] = {
    db.run(UserApplicationTable.result)
  }

  def deleteEntity(userId: String, applicationId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).delete)
  }

}