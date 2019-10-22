package repository.users.impl.cockroachdb.tables

import java.time.LocalDateTime

import domain.login.ChangePassword
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserChangePasswordTable(tag: Tag) extends Table[ChangePassword](tag, "user_change_password")  {

  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def oldPassword: Rep[String] = column[String]("old_password")

  def newPassword: Rep[String] = column[String]("new_password")

  def dateTimeChanged: Rep[LocalDateTime] = column[LocalDateTime]("datetime_changed")

  override def * : ProvenShape[ChangePassword] = (userId, oldPassword, newPassword, dateTimeChanged) <> ((ChangePassword.apply _).tupled, ChangePassword.unapply)
}

object UserChangePasswordTable extends TableQuery(new UserChangePasswordTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String): Future[Option[ChangePassword]] = {
    db.run(this.filter(_.userId === userId).result).map(_.headOption)
  }

  def saveEntity(changePassword: ChangePassword): Future[Option[ChangePassword]] = {
    db.run(
      (this returning this).insertOrUpdate(changePassword)
    )
  }

  def getEntities: Future[Seq[ChangePassword]] = {
    db.run(UserChangePasswordTable.result)
  }

  def createTable: Boolean = {
    db.run(
      UserChangePasswordTable.schema.createIfNotExists
    ).isCompleted
  }
}
