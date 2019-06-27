package repository.mail.Impl.cockcroachdb.tables


import java.time.LocalDateTime

import domain.mail.MailConfig
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MailConfigTable(tag: Tag) extends Table[MailConfigTable](tag,"MAILCONFIG") {
  def siteId: Rep[String] = column[String]("SITE_ID")
  def id: Rep[String] = column[String]("MAILCONFIG_ID",O.PrimaryKey)
  def key: Rep[String] = column[String]("MAILCONFIG_KEY")
  def value: Rep[String] = column[String]("VALUE")
  def host: Rep[String] = column[String]("HOST")
  def port: Rep[String] = column[String]("PORT")
  def state: Rep[String] = column[String]("STATE")
  def date: Rep[LocalDateTime] = column[LocalDateTime]("DATE")
  def * : ProvenShape[MailConfig] = (id, siteId,key, value, host,port,state,date) <> ((MailConfig.apply _).tupled, MailConfig.unapply)
}

object MailConfigTable extends TableQuery(new MailConfigTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[MailConfig]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(mailConfig: MailConfig): Future[MailConfig] = {
    db.run(this returning this.map(_.id) into ((acc, id) => acc.copy(id = id)) += mailConfig)
  }

  def getEntities: Future[Seq[MailConfig]] = {
    db.run(MailConfigTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable = {
    db.run(
      MailApiTable.schema.createIfNotExists
    ).isCompleted
  }

}

/*

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

  def createTable = {
    db.run(
      MailApiTable.schema.createIfNotExists
    ).isCompleted
  }

}
 */