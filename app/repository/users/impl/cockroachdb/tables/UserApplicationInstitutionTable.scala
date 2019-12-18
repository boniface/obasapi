package repository.users.impl.cockroachdb.tables

import domain.users.UserApplicationInstitution
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
class UserApplicationInstitutionTableCreate(tag: Tag) extends Table[UserApplicationInstitution](tag, "user_application_institution") {
  def userId: Rep[String] = column[String]("user_id")

  def applicationId: Rep[String] = column[String]("application_id")

  def institutionId: Rep[String] = column[String]("institution_id")

  def * : ProvenShape[UserApplicationInstitution] = (userId, applicationId, institutionId) <> ((UserApplicationInstitution.apply _).tupled, UserApplicationInstitution.unapply)

  def pk = primaryKey("pk_user_application_institution", (userId, applicationId))
}

object UserApplicationInstitutionTableCreate extends TableQuery(new UserApplicationInstitutionTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = {
    db.run(
      UserApplicationInstitutionTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 * @param tag
 */
class UserApplicationInstitutionTable(tag: Tag) extends Table[UserApplicationInstitution](tag, "user_application_institution") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def applicationId: Rep[String] = column[String]("application_id", O.PrimaryKey)

  def institutionId: Rep[String] = column[String]("institution_id")

  def * : ProvenShape[UserApplicationInstitution] = (userId, applicationId, institutionId) <> ((UserApplicationInstitution.apply _).tupled, UserApplicationInstitution.unapply)
}

object UserApplicationInstitutionTable extends TableQuery(new UserApplicationInstitutionTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, applicationId: String): Future[Option[UserApplicationInstitution]] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).result).map(_.headOption)
  }

  def getEntitiesForUser(userId: String): Future[Seq[UserApplicationInstitution]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def saveEntity(userApplicationInstitution: UserApplicationInstitution): Future[Option[UserApplicationInstitution]] = {
    db.run(
      (this returning this).insertOrUpdate(userApplicationInstitution)
    )
  }

  def getEntities: Future[Seq[UserApplicationInstitution]] = {
    db.run(UserApplicationInstitutionTable.result)
  }

  def deleteEntity(userId: String, applicationId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).delete)
  }

}
