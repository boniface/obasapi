package repository.users.impl.cockroachdb.tables

import domain.users.UserMatricSubject
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Used for DDL (to create table with composite key)
 * @param tag
 */
class UserMatricSubjectTableCreate(tag: Tag) extends Table[UserMatricSubject](tag, "user_matric_subject") {
  def userId: Rep[String] = column[String]("user_id")

  def subjectId: Rep[String] = column[String]("subject_id")

  def subjectMark: Rep[Double] = column[Double]("subject_mark")

  def * : ProvenShape[UserMatricSubject] = (userId, subjectId, subjectMark) <> ((UserMatricSubject.apply _).tupled, UserMatricSubject.unapply)

  def pk = primaryKey("pk_user_matric_subject", (userId, subjectId))
}

object UserMatricSubjectTableCreate extends TableQuery(new UserMatricSubjectTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      UserMatricSubjectTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 *
 * @param tag
 */
class UserMatricSubjectTable(tag: Tag) extends Table[UserMatricSubject](tag, "user_matric_subject") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def subjectId: Rep[String] = column[String]("subject_id", O.PrimaryKey)

  def subjectMark: Rep[Double] = column[Double]("subject_mark")

  def * : ProvenShape[UserMatricSubject] = (userId, subjectId, subjectMark) <> ((UserMatricSubject.apply _).tupled, UserMatricSubject.unapply)
}

object UserMatricSubjectTable extends TableQuery(new UserMatricSubjectTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, subjectId: String): Future[Option[UserMatricSubject]] = {
    db.run(this.filter(_.userId === userId).filter(_.subjectId === subjectId).result).map(_.headOption)
  }

  def saveEntity(userMatricSubject: UserMatricSubject): Future[Option[UserMatricSubject]] = {
    db.run(
      (this returning this).insertOrUpdate(userMatricSubject)
    )
  }

  def getEntitiesForUser(userId: String): Future[Seq[UserMatricSubject]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def getEntities: Future[Seq[UserMatricSubject]] = {
    db.run(UserMatricSubjectTable.result)
  }

  def deleteEntity(userId: String, subjectId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.subjectId === subjectId).delete)
  }

}
