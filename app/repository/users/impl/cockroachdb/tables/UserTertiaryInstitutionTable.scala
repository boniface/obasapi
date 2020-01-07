package repository.users.impl.cockroachdb.tables

import domain.users.UserTertiaryInstitution
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
class UserTertiaryInstitutionTableCreate(tag: Tag) extends Table[UserTertiaryInstitution](tag, "user_tertiary_institution") {
  def userId: Rep[String] = column[String]("user_id")

  def applicationId: Rep[String] = column[String]("application_id")

  def institutionId: Rep[String] = column[String]("institution_id")

  def debtAmount: Rep[Double] = column[Double]("debt_amount")

  def * : ProvenShape[UserTertiaryInstitution] = (userId, applicationId, institutionId, debtAmount) <> ((UserTertiaryInstitution.apply _).tupled, UserTertiaryInstitution.unapply)

  def pk = primaryKey("pk_user_tertiary_institution", (userId, applicationId))
}

object UserTertiaryInstitutionTableCreate extends TableQuery(new UserTertiaryInstitutionTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = {
    db.run(
      UserTertiaryInstitutionTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

class UserTertiaryInstitutionTable(tag: Tag) extends Table[UserTertiaryInstitution](tag, "user_tertiary_institution") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def applicationId: Rep[String] = column[String]("application_id", O.PrimaryKey)

  def institutionId: Rep[String] = column[String]("institution_id")

  def debtAmount: Rep[Double] = column[Double]("debt_amount")

  def * : ProvenShape[UserTertiaryInstitution] = (userId, applicationId, institutionId, debtAmount) <> ((UserTertiaryInstitution.apply _).tupled, UserTertiaryInstitution.unapply)
}

object UserTertiaryInstitutionTable extends TableQuery(new UserTertiaryInstitutionTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, applicationId: String): Future[Option[UserTertiaryInstitution]] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).result).map(_.headOption)
  }

  def getEntitiesForUser(userId: String): Future[Seq[UserTertiaryInstitution]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def saveEntity(userTertiaryInstitution: UserTertiaryInstitution): Future[Option[UserTertiaryInstitution]] = {
    db.run(
      (this returning this).insertOrUpdate(userTertiaryInstitution)
    )
  }

  def getEntities: Future[Seq[UserTertiaryInstitution]] = {
    db.run(UserTertiaryInstitutionTable.result)
  }

  def deleteEntity(userId: String, applicationId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).delete)
  }

}
