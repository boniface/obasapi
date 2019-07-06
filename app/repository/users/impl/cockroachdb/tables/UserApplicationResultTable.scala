package repository.users.impl.cockroachdb.tables

import domain.users.UserApplicationResult
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserApplicationResultTable(tag: Tag) extends Table[UserApplicationResult](tag, "USERAPPLICATIONRESULT") {
  def userApplicationResultId: Rep[String] = column[String]("USER_APPLICATION_RESULT_ID", O.PrimaryKey)

  def description: Rep[String] = column[String]("PHYSICAL_ADDRESS")

  def * : ProvenShape[UserApplicationResult] = (userApplicationResultId, description) <> ((UserApplicationResult.apply _).tupled, UserApplicationResult.unapply)
}

object UserApplicationResultTable extends TableQuery(new UserApplicationResultTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userApplicationResultId: String): Future[Option[UserApplicationResult]] = {
    db.run(this.filter(_.userApplicationResultId === userApplicationResultId).result).map(_.headOption)
  }

  def saveEntity(userApplicationResult: UserApplicationResult): Future[UserApplicationResult] = {
    db.run(this returning this.map(_.userApplicationResultId) into ((acc, userApplicationResultId) => acc.copy(userApplicationResultId = userApplicationResultId)) += userApplicationResult)
  }

  def getEntities: Future[Seq[UserApplicationResult]] = {
    db.run(UserApplicationResultTable.result)
  }

  def deleteEntity(userApplicationResultId: String): Future[Int] = {
    db.run(this.filter(_.userApplicationResultId === userApplicationResultId).delete)
  }

  def createTable = {
    db.run(
      UserApplicationResultTable.schema.createIfNotExists
    ).isCompleted
  }

}