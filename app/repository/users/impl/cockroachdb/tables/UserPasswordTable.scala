package repository.users.impl.cockroachdb.tables

import domain.users.UserPassword
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserPasswordTable(tag: Tag) extends Table[UserPassword](tag, "USERPASSWORD") {
  def userId: Rep[String] = column[String]("USER_ID", O.PrimaryKey)

  def password: Rep[String] = column[String]("PASSWORD")

  def * : ProvenShape[UserPassword] = (userId, password) <> ((UserPassword.apply _).tupled, UserPassword.unapply)
}

object UserPasswordTable extends TableQuery(new UserPasswordTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String): Future[Option[UserPassword]] = {
    db.run(this.filter(_.userId === userId).result).map(_.headOption)
  }

  def saveEntity(userPassword: UserPassword): Future[UserPassword] = {
    db.run(this returning this.map(_.userId) into ((acc, userId) => acc.copy(userId = userId)) += userPassword)
  }

  def getEntities: Future[Seq[UserPassword]] = {
    db.run(UserPasswordTable.result)
  }

  def deleteEntity(userId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).delete)
  }

  def createTable = {
    db.run(
      UserPasswordTable.schema.createIfNotExists
    ).isCompleted
  }

}