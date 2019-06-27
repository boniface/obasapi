package repository.users.Impl.cockroachdb.tables

import domain.users.UserRole
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserRoleTable(tag: Tag) extends Table[UserRole](tag, "USERROLE") {
  def userRoleId: Rep[String] = column[String]("USER_ROLE_ID", O.PrimaryKey)

  def name: Rep[String] = column[String]("NAME")

  def description: Rep[String] = column[String]("DESCRIPTION")

  def * : ProvenShape[UserRole] = (userRoleId, name, description) <> ((UserRole.apply _).tupled, UserRole.unapply)
}

object UserRoleTable extends TableQuery(new UserRoleTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userRoleId: String): Future[Option[UserRole]] = {
    db.run(this.filter(_.userRoleId === userRoleId).result).map(_.headOption)
  }

  def saveEntity(userRole: UserRole): Future[UserRole] = {
    db.run(this returning this.map(_.userRoleId) into ((acc, userRoleId) => acc.copy(userRoleId = userRoleId)) += userRole)
  }

  def getEntities: Future[Seq[UserRole]] = {
    db.run(UserRoleTable.result)
  }

  def deleteEntity(userRoleId: String): Future[Int] = {
    db.run(this.filter(_.userRoleId === userRoleId).delete)
  }

  def createTable = {
    db.run(
      UserRoleTable.schema.createIfNotExists
    ).isCompleted
  }

}