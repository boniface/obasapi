package repository.mail.impl.cockcroachdb.tables


import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import domain.mail.EmailMessage

class EmailMessageTable(tag: Tag) extends Table[EmailMessage](tag, "EMAIL_MESSAGE_TABLE") {
  def email: Rep[String] = column[String]("EMAIL", O.PrimaryKey)

  def subject: Rep[String] = column[String]("SUBJECT")

  def content: Rep[String] = column[String]("CONTENT")

  def * : ProvenShape[EmailMessage] = (email, subject, content) <> ((EmailMessage.apply _).tupled, EmailMessage.unapply)
}

object EmailMessageTable extends TableQuery(new EmailMessageTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(email: String): Future[Option[EmailMessage]] = {
    db.run(this.filter(_.email === email).result).map(_.headOption)
  }

  def saveEntity(mailApi: EmailMessage): Future[Option[EmailMessage]] = {
    db.run(
      (this returning this).insertOrUpdate(mailApi)
    )
  }

  def getEntities: Future[Seq[EmailMessage]] = {
    db.run(EmailMessageTable.result)
  }

  def deleteEntity(email: String): Future[Int] = {
    db.run(this.filter(_.email === email).delete)
  }

  def createTable: Boolean = {
    db.run(
      EmailMessageTable.schema.createIfNotExists
    ).isCompleted
  }

}
