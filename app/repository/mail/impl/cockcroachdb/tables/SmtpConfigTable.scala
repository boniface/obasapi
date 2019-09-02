package repository.mail.impl.cockcroachdb.tables

import domain.mail.SmtpConfig
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future


class SmtpConfigTable(tag: Tag) extends Table[SmtpConfig](tag, "smtp_config") {
  def id: Rep[String] = column[String]("smtp_config_id", O.PrimaryKey)

  def port: Rep[Int] = column[Int]("port")

  def host: Rep[String] = column[String]("host")

  def username: Rep[String] = column[String]("username")

  def password: Rep[String] = column[String]("password")

  def * : ProvenShape[SmtpConfig] = (id,port,host,username,password) <> ((SmtpConfig.apply _).tupled, SmtpConfig.unapply)
}

object SmtpConfigTable extends TableQuery(new SmtpConfigTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[SmtpConfig]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(smtpConfig: SmtpConfig): Future[Option[SmtpConfig]] = {
    db.run(
      (this returning this).insertOrUpdate(smtpConfig)
    )
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

