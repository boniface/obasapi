package repository.security.impl.cockroach.tables

import java.time.LocalDateTime

import domain.security.ApiKeys
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class ApiKeysTable(tag: Tag) extends Table[ApiKeys](tag, "api_keys") {
  def id: Rep[String] = column[String]("apikeys_id", O.PrimaryKey)

  def value: Rep[String] = column[String]("apikeys_value")

  def status: Rep[String] = column[String]("status")

  def date: Rep[LocalDateTime] = column[LocalDateTime]("datetime")

  def * : ProvenShape[ApiKeys] = (id, value, status, date) <> ((ApiKeys.apply _).tupled, ApiKeys.unapply)
}

object ApiKeysTable extends TableQuery(new ApiKeysTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[ApiKeys]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(apiKeys: ApiKeys): Future[Option[ApiKeys]] = {
    db.run(
      (this returning this).insertOrUpdate(apiKeys)
    )
  }

  def getEntities: Future[Seq[ApiKeys]] = {
    db.run(ApiKeysTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable: Boolean = {
    db.run(
      ApiKeysTable.schema.createIfNotExists
    ).isCompleted
  }

}