package repository.users.impl.cockroachdb.tables

import domain.users.UserRelative
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserRelativeTable(tag: Tag) extends Table[UserRelative](tag, "user_relative") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def name: Rep[String] = column[String]("name")

  def cellphone: Rep[String] = column[String]("cellphone")

  def email: Rep[String] = column[String]("email")

  def relationship: Rep[String] = column[String]("relationship")

  def * : ProvenShape[UserRelative] = (userId, name, cellphone, relationship, email) <> ((UserRelative.apply _).tupled, UserRelative.unapply)
}

object UserRelativeTable extends TableQuery(new UserRelativeTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String): Future[Option[UserRelative]] = {
    db.run(this.filter(_.userId === userId).result).map(_.headOption)
  }

  def saveEntity(userRelative: UserRelative): Future[Option[UserRelative]] = {
    db.run(
      (this returning this).insertOrUpdate(userRelative)
    )
  }

  def getEntities: Future[Seq[UserRelative]] = {
    db.run(UserRelativeTable.result)
  }

  def deleteEntity(userId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).delete)
  }

  def createTable = {
    db.run(
      UserRelativeTable.schema.createIfNotExists
    ).isCompleted
  }

}