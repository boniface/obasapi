package repository.users.impl.cockroachdb.tables

import domain.users.UserSubject
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
class UserSubjectTableCreate(tag: Tag) extends Table[UserSubject](tag, "user_subject") {

  def userId: Rep[String] = column[String]("user_id")

  def institutionId: Rep[String] = column[String]("institution_id")

  def subjectId: Rep[String] = column[String]("subject_id")

  def subjectMark: Rep[Double] = column[Double]("subject_mark")

  def * : ProvenShape[UserSubject] = (userId, institutionId, subjectId, subjectMark) <> ((UserSubject.apply _).tupled, UserSubject.unapply)

  def pk = primaryKey("pk_user_subject", (userId, institutionId, subjectId))
}

object UserSubjectTableCreate extends TableQuery(new UserCourseTableCreate(_)) {

  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = {
    db.run(
      UserSubjectTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * For DML
 * @param tag
 */
class UserSubjectTable(tag: Tag) extends Table[UserSubject](tag, "user_subject") {

  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def institutionId: Rep[String] = column[String]("institution_id", O.PrimaryKey)

  def subjectId: Rep[String] = column[String]("subject_id", O.PrimaryKey)

  def subjectMark: Rep[Double] = column[Double]("subject_mark")

  def * : ProvenShape[UserSubject] = (userId, institutionId, subjectId, subjectMark) <> ((UserSubject.apply _).tupled, UserSubject.unapply)

}

object UserSubjectTable extends TableQuery(new UserSubjectTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, institutionId: String, subjectId: String): Future[Option[UserSubject]] = {
    db.run(
      this.filter(_.userId === userId).filter(_.institutionId === institutionId)
        .filter(_.subjectId === subjectId).result
    ).map(_.headOption)
  }

  def saveEntity(userSubject: UserSubject): Future[Option[UserSubject]] = {
    db.run(
      (this returning this).insertOrUpdate(userSubject)
    )
  }

  def getEntitiesForUserPerInstitution(userId: String, institutionId: String): Future[Seq[UserSubject]] = {
    db.run(
      this.filter(_.userId === userId).filter(_.institutionId === institutionId).result
    )
  }

  def getEntitiesForUser(userId: String): Future[Seq[UserSubject]] = {
    db.run(this.filter(_.userId === userId).result)
  }

  def getEntities: Future[Seq[UserSubject]] = {
    db.run(UserSubjectTable.result)
  }

  def deleteEntity(userId: String, institutionId: String, subjectId: String): Future[Int] = {
    db.run(
      this.filter(_.userId === userId)
        .filter(_.institutionId === institutionId)
        .filter(_.subjectId === subjectId).delete
    )
  }
}