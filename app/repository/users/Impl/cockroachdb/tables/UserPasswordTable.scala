package repository.users.Impl.cockroachdb.tables

import domain.users.UserPassword
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserPasswordTable(tag: Tag) extends Table[UserPassword](tag, "USERPASSWORD") {
  def userPasswordId: Rep[String] = column[String]("USER_PASSWORD_ID", O.PrimaryKey)

  def password: Rep[String] = column[String]("PASSWORD")

  def * : ProvenShape[UserPassword] = (userPasswordId, password) <> ((UserPassword.apply _).tupled, UserPassword.unapply)
}

object UserPasswordTable extends TableQuery(new UserPasswordTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userPasswordId: String): Future[Option[UserPassword]] = {
    db.run(this.filter(_.userPasswordId === userPasswordId).result).map(_.headOption)
  }

  def saveEntity(userPassword: UserPassword): Future[UserPassword] = {
    db.run(this returning this.map(_.userPasswordId) into ((acc, userPasswordId) => acc.copy(userPasswordId = userPasswordId)) += userPassword)
  }

  def getEntities: Future[Seq[UserPassword]] = {
    db.run(UserPasswordTable.result)
  }

  def deleteEntity(userPasswordId: String): Future[Int] = {
    db.run(this.filter(_.userPasswordId === userPasswordId).delete)
  }

  def createTable = {
    db.run(
      UserPasswordTable.schema.createIfNotExists
    ).isCompleted
  }

}