package repository.users.impl.cockroachdb.tables

import domain.users.UserContacts
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserContactsTable(tag: Tag) extends Table[UserContacts](tag, "user_contacts") {
  def userContactId: Rep[String] = column[String]("user_contact_id", O.PrimaryKey)

  def cellNumber: Rep[String] = column[String]("cell_number")

  def alternativeNumber: Rep[String] = column[String]("alternative_number")

  def alternativeEmail: Rep[String] = column[String]("alternative_email")

  def * : ProvenShape[UserContacts] = (userContactId, cellNumber, alternativeNumber, alternativeEmail) <> ((UserContacts.apply _).tupled, UserContacts.unapply)
}

object UserContactsTable extends TableQuery(new UserContactsTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userContactId: String): Future[Option[UserContacts]] = {
    db.run(this.filter(_.userContactId === userContactId).result).map(_.headOption)
  }

  def saveEntity(userContacts: UserContacts): Future[Option[UserContacts]] = {
    db.run(
      (this returning this).insertOrUpdate(userContacts)
    )
  }

  def getEntities: Future[Seq[UserContacts]] = {
    db.run(UserContactsTable.result)
  }

  def deleteEntity(userContactId: String): Future[Int] = {
    db.run(this.filter(_.userContactId === userContactId).delete)
  }

  def createTable = {
    db.run(
      UserContactsTable.schema.createIfNotExists
    ).isCompleted
  }

}