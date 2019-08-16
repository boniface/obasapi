package repository.users.impl.cockroachdb.tables

import domain.users.UserPassword
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserPasswordTable(tag: Tag) extends Table[UserPassword](tag, "USER_PASSWORD") {
  def email: Rep[String] = column[String]("EMAIL", O.PrimaryKey)

  def password: Rep[String] = column[String]("PASSWORD")

  def * : ProvenShape[UserPassword] = (email, password) <> ((UserPassword.apply _).tupled, UserPassword.unapply)
}

object UserPasswordTable extends TableQuery(new UserPasswordTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(email: String): Future[Option[UserPassword]] = {
    db.run(this.filter(_.email === email).result).map(_.headOption)
  }

  def saveEntity(userPassword: UserPassword): Future[Option[UserPassword]] = {
    db.run(
      (this returning this).insertOrUpdate(userPassword)
    )
  }

  def getEntities: Future[Seq[UserPassword]] = {
    db.run(UserPasswordTable.result)
  }

  def deleteEntity(email: String): Future[Int] = {
    db.run(this.filter(_.email === email).delete)
  }

  def createTable = {
    db.run(
      UserPasswordTable.schema.createIfNotExists
    ).isCompleted
  }

}