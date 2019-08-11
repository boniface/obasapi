package repository.users.impl.cockroachdb.tables

import domain.users.UserCommunication
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserCommunicationTable(tag: Tag) extends Table[UserCommunication](tag, "USER_COMMUNICATION") {
  def communicationId: Rep[String] = column[String]("COMMUNICATION_ID", O.PrimaryKey)

  def description: Rep[String] = column[String]("DESCRIPTION")

  def * : ProvenShape[UserCommunication] = (communicationId, description) <> ((UserCommunication.apply _).tupled, UserCommunication.unapply)
}

object UserCommunicationTable extends TableQuery(new UserCommunicationTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(communicationId: String): Future[Option[UserCommunication]] = {
    db.run(this.filter(_.communicationId === communicationId).result).map(_.headOption)
  }

  def saveEntity(userCommunication: UserCommunication): Future[Option[UserCommunication]] = {
    db.run(
      (this returning this).insertOrUpdate(userCommunication)
    )
  }

  def getEntities: Future[Seq[UserCommunication]] = {
    db.run(UserCommunicationTable.result)
  }

  def deleteEntity(communicationId: String): Future[Int] = {
    db.run(this.filter(_.communicationId === communicationId).delete)
  }

  def createTable = {
    db.run(
      UserCommunicationTable.schema.createIfNotExists
    ).isCompleted
  }

}
