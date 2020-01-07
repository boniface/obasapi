package repository.users.impl.cockroachdb.tables

import domain.users.UserContacts
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
class UserContactsTableCreate(tag: Tag) extends Table[UserContacts](tag, "user_contacts") {

  def userId: Rep[String] = column[String]("user_id")

  def contactTypeId: Rep[String] = column[String]("contact_type_id")

  def contact: Rep[String] = column[String]("contact")

  def * : ProvenShape[UserContacts] = (userId, contactTypeId, contact) <> ((UserContacts.apply _).tupled, UserContacts.unapply)

  def pk = primaryKey("pk_user_contacts", (userId, contactTypeId))
}

object UserContactsTableCreate extends TableQuery(new UserContactsTableCreate(_)) {

  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      UserContactsTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class UserContactsTable(tag: Tag) extends Table[UserContacts](tag, "user_contacts") {

  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def contactTypeId: Rep[String] = column[String]("contact_type_id", O.PrimaryKey)

  def contact: Rep[String] = column[String]("contact")

  def * : ProvenShape[UserContacts] = (userId, contactTypeId, contact) <> ((UserContacts.apply _).tupled, UserContacts.unapply)
}

object UserContactsTable extends TableQuery(new UserContactsTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, contactTypeId: String): Future[Option[UserContacts]] = {
    db.run(this.filter(_.userId === userId).filter(_.contactTypeId === contactTypeId).result).map(_.headOption)
  }

  def getEntityForUser(userId: String): Future[Seq[UserContacts]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def saveEntity(userContacts: UserContacts): Future[Option[UserContacts]] = {
    db.run(
      (this returning this).insertOrUpdate(userContacts)
    )
  }

  def getEntities: Future[Seq[UserContacts]] = {
    db.run(UserContactsTable.result)
  }

  def deleteEntity(userId: String, contactTypeId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.contactTypeId === contactTypeId).delete)
  }

}