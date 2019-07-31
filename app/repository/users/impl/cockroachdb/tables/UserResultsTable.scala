package repository.users.impl.cockroachdb.tables

import domain.users.UserResults
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserResultsTable(tag: Tag) extends Table[UserResults](tag, "USERRESULTS") {
  def userResultsId: Rep[String] = column[String]("USER_RESULTS_ID", O.PrimaryKey)

  def description: Rep[String] = column[String]("DESCRIPTION")

  def * : ProvenShape[UserResults] = (userResultsId, description) <> ((UserResults.apply _).tupled, UserResults.unapply)
}

object UserResultsTable extends TableQuery(new UserResultsTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userResultsId: String): Future[Option[UserResults]] = {
    db.run(this.filter(_.userResultsId === userResultsId).result).map(_.headOption)
  }

  def saveEntity(userResults: UserResults): Future[Option[UserResults]] = {
    db.run(
      (this returning this).insertOrUpdate(userResults)
    )
  }

  def getEntities: Future[Seq[UserResults]] = {
    db.run(UserResultsTable.result)
  }

  def deleteEntity(userResultsId: String): Future[Int] = {
    db.run(this.filter(_.userResultsId === userResultsId).delete)
  }

  def createTable = {
    db.run(
      UserResultsTable.schema.createIfNotExists
    ).isCompleted
  }

}