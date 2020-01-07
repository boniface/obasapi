package repository.login.impl.cockroach.tables

import domain.login.LoginToken
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class LoginTokenTable(tag: Tag) extends Table[LoginToken](tag, "login_token") {
  def email: Rep[String] = column[String]("email", O.PrimaryKey)

  def token: Rep[String] = column[String]("token")


  def * : ProvenShape[LoginToken] = (email, token) <> ((LoginToken.apply _).tupled, LoginToken.unapply)
}

object LoginTokenTable extends TableQuery(new LoginTokenTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(email: String): Future[Option[LoginToken]] = {
    db.run(this.filter(_.email === email).result).map(_.headOption)
  }

  def saveEntity(loginToken: LoginToken): Future[Option[LoginToken]] = {
    db.run(
      (this returning this).insertOrUpdate(loginToken)
    )
  }

  def getEntities: Future[Seq[LoginToken]] = {
    db.run(LoginTokenTable.result)
  }

  def deleteEntity(email: String): Future[Int] = {
    db.run(this.filter(_.email === email).delete)
  }

  def createTable = {
    db.run(
      LoginTokenTable.schema.createIfNotExists
    ).isCompleted
  }


}