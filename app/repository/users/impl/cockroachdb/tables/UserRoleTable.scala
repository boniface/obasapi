package repository.users.impl.cockroachdb.tables

import domain.users.UserRole
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserRoleTable(tag: Tag) extends Table[UserRole](tag, "USERROLE") {
  def userId: Rep[String] = column[String]("USER_ID", O.PrimaryKey)

  def roleId: Rep[String] = column[String]("ROLE_ID")

  def * : ProvenShape[UserRole] = (userId, roleId) <> ((UserRole.apply _).tupled, UserRole.unapply)
}

object UserRoleTable extends TableQuery(new UserRoleTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String): Future[Option[UserRole]] = {
    db.run(this.filter(_.userId === userId).result).map(_.headOption)
  }

  def saveEntity(userRole: UserRole): Future[UserRole] = {
    db.run(this returning this.map(_.userId) into ((acc, userId) => acc.copy(userId = userId)) += userRole)
  }

  def getEntities: Future[Seq[UserRole]] = {
    db.run(UserRoleTable.result)
  }

  def deleteEntity(userId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).delete)
  }

  def createTable = {
    db.run(
      UserRoleTable.schema.createIfNotExists
    ).isCompleted
  }

}