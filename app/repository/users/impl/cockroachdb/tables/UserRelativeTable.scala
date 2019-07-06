package repository.users.impl.cockroachdb.tables

import domain.users.UserRelative
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserRelativeTable(tag: Tag) extends Table[UserRelative](tag, "USERRELATIVE") {
  def userRelativeId: Rep[String] = column[String]("USER_RELATIVE_ID", O.PrimaryKey)

  def name: Rep[String] = column[String]("NAME")

  def cellphone: Rep[String] = column[String]("CELLPHONE")

  def relationship: Rep[String] = column[String]("RELATIONSHIP")

  def email: Rep[String] = column[String]("EMAIL")

  def * : ProvenShape[UserRelative] = (userRelativeId, name, cellphone, relationship, email) <> ((UserRelative.apply _).tupled, UserRelative.unapply)
}

object UserRelativeTable extends TableQuery(new UserRelativeTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userRelativeId: String): Future[Option[UserRelative]] = {
    db.run(this.filter(_.userRelativeId === userRelativeId).result).map(_.headOption)
  }

  def saveEntity(userRelative: UserRelative): Future[UserRelative] = {
    db.run(this returning this.map(_.userRelativeId) into ((acc, userRelativeId) => acc.copy(userRelativeId = userRelativeId)) += userRelative)
  }

  def getEntities: Future[Seq[UserRelative]] = {
    db.run(UserRelativeTable.result)
  }

  def deleteEntity(userRelativeId: String): Future[Int] = {
    db.run(this.filter(_.userRelativeId === userRelativeId).delete)
  }

  def createTable = {
    db.run(
      UserRelativeTable.schema.createIfNotExists
    ).isCompleted
  }

}