package repository.mail.impl.cockcroachdb.tables


import java.time.LocalDateTime
import domain.mail.MailConfig
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class MailConfigTable(tag: Tag) extends Table[MailConfig](tag,"mail_config") {

  def id: Rep[String] = column[String]("mail_config_id",O.PrimaryKey)

  def siteId: Rep[String] = column[String]("site_id")

  def key: Rep[String] = column[String]("mail_config_key")

  def value: Rep[String] = column[String]("value")

  def host: Rep[String] = column[String]("host")

  def port: Rep[String] = column[String]("post")

  def state: Rep[String] = column[String]("state")

  def date: Rep[LocalDateTime] = column[LocalDateTime]("date")

  def * : ProvenShape[MailConfig] = (siteId,id,key, value, host,port,state,date) <> ((MailConfig.apply _).tupled, MailConfig.unapply)
}

object MailConfigTable extends TableQuery(new MailConfigTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[MailConfig]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(mailConfig: MailConfig): Future[Option[MailConfig]] = {
    db.run(
      (this returning this).insertOrUpdate(mailConfig)
    )
  }

  def getEntities: Future[Seq[MailConfig]] = {
    db.run(MailConfigTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      MailConfigTable.schema.createIfNotExists
    ).isCompleted
  }
}


