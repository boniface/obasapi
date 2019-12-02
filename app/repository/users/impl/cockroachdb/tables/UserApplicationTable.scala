package repository.users.impl.cockroachdb.tables

import domain.users.UserApplication
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserApplicationTable(tag: Tag) extends Table[UserApplication](tag, "user_application_result") {
  def userApplicationResultId: Rep[String] = column[String]("user_application_result_id", O.PrimaryKey)

  def description: Rep[String] = column[String]("description")

  def * : ProvenShape[UserApplication] = (userApplicationResultId, description) <> ((UserApplication.apply _).tupled, UserApplication.unapply)
}

object UserApplicationTable extends TableQuery(new UserApplicationTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userApplicationResultId: String): Future[Option[UserApplication]] = {
    db.run(this.filter(_.userApplicationResultId === userApplicationResultId).result).map(_.headOption)
  }

  def saveEntity(userApplicationResult: UserApplication): Future[Option[UserApplication]] = {
    db.run(
      (this returning this).insertOrUpdate(userApplicationResult)
    )
  }

  def getEntities: Future[Seq[UserApplication]] = {
    db.run(UserApplicationTable.result)
  }

  def deleteEntity(userApplicationResultId: String): Future[Int] = {
    db.run(this.filter(_.userApplicationResultId === userApplicationResultId).delete)
  }

  def createTable = {
    db.run(
      UserApplicationTable.schema.createIfNotExists
    ).isCompleted
  }

}