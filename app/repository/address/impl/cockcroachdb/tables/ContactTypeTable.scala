package repository.address.impl.cockcroachdb.tables

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import domain.address.ContactType


class ContactTypeTable(tag: Tag) extends Table[ContactType](tag, "CONTACT_TYPE") {

  def contactTypeId: Rep[String] = column[String]("CONTACTTYPEID", O.PrimaryKey)

  def name: Rep[String] = column[String]("NAME")

  def * : ProvenShape[ContactType] = (contactTypeId, name) <> ((ContactType.apply _).tupled, ContactType.unapply)
}

object ContactTypeTable extends TableQuery(new ContactTypeTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(contactTypeId: String): Future[Option[ContactType]] = {
    db.run(this.filter(_.contactTypeId === contactTypeId).result).map(_.headOption)
  }

  def saveEntity(contactType: ContactType): Future[ContactType] = {
    db.run(this returning this.map(_.contactTypeId) into ((acc, contactTypeId) => acc.copy(contactTypeId = contactTypeId)) += contactType)
  }

  def getEntities: Future[Seq[ContactType]] = {
    db.run(ContactTypeTable.result)
  }

  def deleteEntity(contactTypeId: String): Future[Int] = {
    db.run(this.filter(_.contactTypeId === contactTypeId).delete)
  }

  def createTable = {
    db.run(
      ContactTypeTable.schema.createIfNotExists
    ).isCompleted
  }

}