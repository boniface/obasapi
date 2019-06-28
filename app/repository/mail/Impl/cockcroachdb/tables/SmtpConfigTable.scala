package repository.mail.Impl.cockcroachdb.tables

import domain.mail.SmtpConfig
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/*class SmtpConfigTable {

//  object id extends StringColumn with PartitionKey
//
//  object port extends IntColumn
//
//  object host extends StringColumn
//
//  object username extends StringColumn
//
//  object password extends StringColumn

}*/


class SmtpConfigTable(tag: Tag) extends Table[SmtpConfig](tag, "SMTPCONFIG") {

  def id: Rep[String] = column[String]("SMTPCONFIG_ID", O.PrimaryKey)

  def host: Rep[String] = column[String]("HOST")

  def port: Rep[Int] = column[Int]("PORT")

  def username: Rep[String] = column[String]("USERNAME")

  def password: Rep[String] = column[String]("PASSWORD")


  def * : ProvenShape[SmtpConfig] = (id, port, host,  username,password) <> ((SmtpConfig.apply _).tupled, SmtpConfig.unapply)
}

object SmtpConfigTable extends TableQuery(new MailApiTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[SmtpConfigTable]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(smtpConfig: SmtpConfig): Future[SmtpConfig] = {
    db.run(this returning this.map(_.id) into ((acc, id) => acc.copy(id = id)) += smtpConfig)
  }

  def getEntities: Future[Seq[SmtpConfig]] = {
    db.run(SmtpConfigTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      SmtpConfigTable.schema.createIfNotExists
    ).isCompleted
  }

}