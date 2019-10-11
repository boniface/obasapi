package repository.users.impl.cockroachdb.tables

import java.time.LocalDateTime

import domain.users.User
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserTable(tag: Tag) extends Table[User](tag, "user") {
  def email: Rep[String] = column[String]("email", O.PrimaryKey)

  def idNumber: Rep[String] = column[String]("id_number")

  def firstName: Rep[String] = column[String]("first_name")

  def middleName: Rep[String] = column[String]("middle_name")

  def lastName: Rep[String] = column[String]("last_name")

  def dateOfBirth: Rep[LocalDateTime] = column[LocalDateTime]("date_of_birth")

  def * : ProvenShape[User] = (email, idNumber, firstName, middleName, lastName, dateOfBirth) <> ((User.apply _).tupled, User.unapply)
}

object UserTable extends TableQuery(new UserTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(email: String): Future[Option[User]] = {
    db.run(this.filter(_.email === email).result).map(_.headOption)
  }

  def saveEntity(user: User): Future[Option[User]] = {
    db.run(
      (this returning this).insertOrUpdate(user)
    )
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