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

  def locationId: Rep[String] = column[String]("location_id")

  override def * : ProvenShape[UserTown] = (userId, locationId) <> ((UserTown.apply _).tupled, UserTown.unapply)
}

object UserTownTable extends TableQuery(new UserTownTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String): Future[Option[UserTown]] = {
    db.run(this.filter(_.userId === userId).result).map(_.headOption)
  }

  def saveEntity(userDistrict: UserTown): Future[Option[UserTown]] = {
    db.run(
      (this returning this).insertOrUpdate(userDistrict)
    )
  }

  def getEntities: Future[Seq[UserTown]] = {
    db.run(UserTownTable.result)
  }

  def deleteEntity(userId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).delete)
  }

  def createTable: Boolean = {
    db.run(
      UserTownTable.schema.createIfNotExists
    ).isCompleted
  }

}
