package repository.users.impl.cockroachdb.tables

import domain.users.UserMatricInstitution
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class UserMatricInstitutionTable(tag: Tag) extends Table[UserMatricInstitution](tag, "user_matric_institution") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def institutionId: Rep[String] = column[String]("institution_id")

  def * : ProvenShape[UserMatricInstitution] = (userId, institutionId) <> ((UserMatricInstitution.apply _).tupled, UserMatricInstitution.unapply)
}

object UserMatricInstitutionTable extends TableQuery(new UserMatricInstitutionTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String): Future[Option[UserMatricInstitution]] = {
    db.run(this.filter(_.userId === userId).result).map(_.headOption)
  }

  def saveEntity(userMatricInstitution: UserMatricInstitution): Future[Option[UserMatricInstitution]] = {
    db.run(
      (this returning this).insertOrUpdate(userMatricInstitution)
    )
  }

  def getEntities: Future[Seq[UserMatricInstitution]] = {
    db.run(UserMatricInstitutionTable.result)
  }

  def deleteEntity(userId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).delete)
  }

  def createTable: Boolean = {
    db.run(
      UserMatricInstitutionTable.schema.createIfNotExists
    ).isCompleted
  }

}