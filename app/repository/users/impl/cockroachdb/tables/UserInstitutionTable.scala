package repository.users.impl.cockroachdb.tables

import domain.users.UserInstitution
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Used for DDL (to create table with composite key)
 * @param tag
 */
class UserInstitutionTableCreate(tag: Tag) extends Table[UserInstitution](tag, "user_institution") {
  def userId: Rep[String] = column[String]("user_id")

  def institutionId: Rep[String] = column[String]("institution_id")

  def isCurrent: Rep[Boolean] = column[Boolean]("is_current")

  def * : ProvenShape[UserInstitution] = (userId, institutionId, isCurrent) <> ((UserInstitution.apply _).tupled, UserInstitution.unapply)

  def pk = primaryKey("pk_user_institution", (userId, institutionId))
}

object UserInstitutionTableCreate extends TableQuery(new UserInstitutionTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      UserInstitutionTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class UserInstitutionTable(tag: Tag) extends Table[UserInstitution](tag, "user_institution") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def institutionId: Rep[String] = column[String]("institution_id", O.PrimaryKey)

  def isCurrent: Rep[Boolean] = column[Boolean]("is_current")

  def * : ProvenShape[UserInstitution] = (userId, institutionId, isCurrent) <> ((UserInstitution.apply _).tupled, UserInstitution.unapply)
}

object UserInstitutionTable extends TableQuery(new UserInstitutionTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, institutionId: String): Future[Option[UserInstitution]] = {
    db.run(this.filter(_.userId === userId).filter(_.institutionId === institutionId).result).map(_.headOption)
  }

  def getEntitiesForUser(userId: String): Future[Seq[UserInstitution]] = {
    db.run(this.filter(_.userId === userId).result)
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

}