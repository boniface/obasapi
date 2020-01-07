package repository.security.impl.cockroach.tables

import domain.security.ResetToken
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ResetTokenTable(tag: Tag) extends Table[ResetToken](tag, "reset_tokens") {
  def resetokenvalue: Rep[String] = column[String]("reset_token_value", O.PrimaryKey)
  def email: Rep[String] = column[String]("email")

  def status: Rep[String] = column[String]("status")

  def * : ProvenShape[ResetToken] = (resetokenvalue, email, status) <> ((ResetToken.apply _).tupled, ResetToken.unapply)
}

object ResetTokenTable extends TableQuery(new ResetTokenTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(resetTokenValue: String): Future[Option[ResetToken]] = {
    db.run(this.filter(_.resetokenvalue === resetTokenValue).result).map(_.headOption)
  }

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