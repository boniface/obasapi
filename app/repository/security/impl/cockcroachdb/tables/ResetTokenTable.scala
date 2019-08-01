package repository.security.impl.cockcroachdb.tables

import domain.security.ResetToken
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

//resetokenvalue: String, email: String

class ResetTokenTable(tag: Tag) extends Table[ResetToken](tag, "RESETTOKENS") {
  def resetokenvalue: Rep[String] = column[String]("RESETTOKENVALUE", O.PrimaryKey)
  def email: Rep[String] = column[String]("EMAIL")

  def status: Rep[String] = column[String]("STATUS")

  def * : ProvenShape[ResetToken] = (resetokenvalue, email) <> ((ResetToken.apply _).tupled, ResetToken.unapply)
}

object ResetTokenTable extends TableQuery(new ResetTokenTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(resetokenvalue: String): Future[Option[ResetToken]] = {
    db.run(this.filter(_.resetokenvalue === resetokenvalue).result).map(_.headOption)
  }

  /*def saveEntity(apiKeys: ResetToken): Future[ResetToken] = {
    db.run(this returning this.map(_.resetokenvalue) into ((acc, resetokenvalue) => acc.copy(resetokenvalue = resetokenvalue)) += apiKeys)
  }*/
  def saveEntity(apiKeys: ResetToken): Future[Option[ResetToken]] = {
    db.run(
      (this returning this).insertOrUpdate(apiKeys)
    )
  }

  def getEntities: Future[Seq[ResetToken]] = {
    db.run(ResetTokenTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.resetokenvalue === id).delete)
  }

  def createTable: Boolean = {
    db.run(
      ResetTokenTable.schema.createIfNotExists
    ).isCompleted
  }

}