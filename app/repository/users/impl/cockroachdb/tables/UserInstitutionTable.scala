package repository.users.impl.cockroachdb.tables

import domain.users.UserInstitution
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserInstitutionTable(tag: Tag) extends Table[UserInstitution](tag, "user_institution") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def institutionId: Rep[String] = column[String]("institution_id")

  def * : ProvenShape[UserInstitution] = (userId, institutionId) <> ((UserInstitution.apply _).tupled, UserInstitution.unapply)
}

object UserInstitutionTable extends TableQuery(new UserInstitutionTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String): Future[Option[UserInstitution]] = {
    db.run(this.filter(_.userId === userId).result).map(_.headOption)
  }

  def saveEntity(userInstitution: UserInstitution): Future[Option[UserInstitution]] = {
    db.run(
      (this returning this).insertOrUpdate(userInstitution)
    )
  }

  def getEntities: Future[Seq[UserInstitution]] = {
    db.run(UserInstitutionTable.result)
  }

  def deleteEntity(userId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).delete)
  }

  def createTable = {
    db.run(
      UserInstitutionTable.schema.createIfNotExists
    ).isCompleted
  }

}