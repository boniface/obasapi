package repository.users.impl.cockroachdb.tables

import domain.users.UserTertiarySubject
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
class UserTertiarySubjectTableCreate(tag: Tag) extends Table[UserTertiarySubject](tag, "user_tertiary_subject") {
  def userId: Rep[String] = column[String]("user_id")

  def applicationId: Rep[String] = column[String]("application_id")

  def subjectId: Rep[String] = column[String]("subject_id")

  def subjectMark: Rep[Double] = column[Double]("subject_mark")

  def * : ProvenShape[UserTertiarySubject] = (userId, applicationId, subjectId, subjectMark) <> ((UserTertiarySubject.apply _).tupled, UserTertiarySubject.unapply)

  def pk = primaryKey("pk_user_tertiary_subject", (userId, applicationId, subjectId))
}

object UserTertiarySubjectTableCreate extends TableQuery(new UserTertiarySubjectTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = {
    db.run(
      UserTertiarySubjectTableCreate.schema.createIfNotExists
    ).isCompleted
  }

}

/**
 * Used for DML
 * @param tag
 */
class UserTertiarySubjectTable(tag: Tag) extends Table[UserTertiarySubject](tag, "user_tertiary_subject") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def applicationId: Rep[String] = column[String]("application_id", O.PrimaryKey)

  def subjectId: Rep[String] = column[String]("subject_id", O.PrimaryKey)

  def subjectMark: Rep[Double] = column[Double]("subject_mark")

  def * : ProvenShape[UserTertiarySubject] = (userId, applicationId, subjectId, subjectMark) <> ((UserTertiarySubject.apply _).tupled, UserTertiarySubject.unapply)
}

object UserTertiarySubjectTable extends TableQuery(new UserTertiarySubjectTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, applicationId: String, subjectId: String): Future[Option[UserTertiarySubject]] = {
    db.run(
      this.filter(_.userId === userId)
        .filter(_.applicationId === applicationId)
        .filter(_.subjectId === subjectId).result
    ).map(_.headOption)
  }

  def getEntitiesForApplication(userId: String, applicationId: String): Future[Seq[UserTertiarySubject]] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).result)
  }

  def getEntitiesForUser(userId: String): Future[Seq[UserTertiarySubject]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def saveEntity(userTertiarySubject: UserTertiarySubject): Future[Option[UserTertiarySubject]] = {
    db.run(
      (this returning this).insertOrUpdate(userTertiarySubject)
    )
  }

  def getEntities: Future[Seq[UserTertiarySubject]] = {
    db.run(UserTertiarySubjectTable.result)
  }

  def deleteEntity(userId: String, applicationId: String, subjectId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).filter(_.subjectId === subjectId).delete)
  }

  def deleteEntitiesForApplication(userId: String, applicationId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.applicationId === applicationId).delete)
  }

}
