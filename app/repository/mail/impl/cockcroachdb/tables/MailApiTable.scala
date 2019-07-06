package repository.mail.impl.cockcroachdb.tables

import domain.mail.MailApi
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class MailApiTable(tag: Tag) extends Table[MailApi](tag, "MAILAPI") {
  def id: Rep[String] = column[String]("MAIL_ID", O.PrimaryKey)

  def key: Rep[String] = column[String]("MAIL_KEY")

  def sender: Rep[String] = column[String]("SENDER")

  def * : ProvenShape[MailApi] = (id, key, sender) <> ((MailApi.apply _).tupled, MailApi.unapply)
}

object MailApiTable extends TableQuery(new MailApiTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[MailApi]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(mailApi: MailApi): Future[MailApi] = {
    db.run(this returning this.map(_.id) into ((acc, id) => acc.copy(id = id)) += mailApi)
  }

  def getEntities: Future[Seq[MailApi]] = {
    db.run(MailApiTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable: Boolean = {
    db.run(
      MailApiTable.schema.createIfNotExists
    ).isCompleted
  }

}