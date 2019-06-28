package repository.users.Impl.cockroachdb.tables

import domain.users.User
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserTable(tag: Tag) extends Table[User](tag, "USER") {
  def email: Rep[String] = column[String]("EMAIL", O.PrimaryKey)

  def firstName: Rep[String] = column[String]("FIRST_NAME")

  def middleName: Rep[String] = column[String]("MIDDLE_NAME")

  def lastName: Rep[String] = column[String]("LAST_NAME")

  def dateOfBirth: Rep[String] = column[String]("DATE_OF_BIRTH")

  def * : ProvenShape[User] = (email, firstName, middleName, lastName, dateOfBirth) <> ((User.apply _).tupled, User.unapply)
}

object UserTable extends TableQuery(new UserTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(email: String): Future[Option[User]] = {
    db.run(this.filter(_.email === email).result).map(_.headOption)
  }

  def saveEntity(user: User): Future[User] = {
    db.run(this returning this.map(_.email) into ((acc, email) => acc.copy(email = email)) += user)
  }

  def getEntities: Future[Seq[User]] = {
    db.run(UserTable.result)
  }

  def deleteEntity(email: String): Future[Int] = {
    db.run(this.filter(_.email === email).delete)
  }

  def createTable = {
    db.run(
      UserTable.schema.createIfNotExists
    ).isCompleted
  }

}