package repository.users.impl.cockroachdb.tables

import domain.users.UserTown
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserTownTable(tag: Tag) extends Table[UserTown](tag, "user_town") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def townCode: Rep[String] = column[String]("town_code")

  override def * : ProvenShape[UserTown] = (userId, townCode) <> ((UserTown.apply _).tupled, UserTown.unapply)
}

object UserTownTable extends TableQuery(new UserTownTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String): Future[Option[UserTown]] = {
    db.run(this.filter(_.userId === userId).result).map(_.headOption)
  }

  def saveEntity(userTown: UserTown): Future[Option[UserTown]] = {
    db.run(
      (this returning this).insertOrUpdate(userTown)
    )
  }

  def getEntities: Future[Seq[UserTown]] = {
    db.run(UserTownTable.result)
  }

  def deleteEntity(userId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).delete)
  }

  def createTable = {
    db.run(
      UserTownTable.schema.createIfNotExists
    ).isCompleted
  }

}
