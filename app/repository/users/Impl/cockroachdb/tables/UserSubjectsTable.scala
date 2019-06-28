package repository.users.Impl.cockroachdb.tables

import domain.users.UserSubjects
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserSubjectsTable(tag: Tag) extends Table[UserSubjects](tag, "USERSUBJECTS") {
  def userSubjectId: Rep[String] = column[String]("USER_SUBJECT_ID", O.PrimaryKey)

  def name: Rep[String] = column[String]("NAME")

  def description: Rep[String] = column[String]("DESCRIPTION")

  def term: Rep[String] = column[String]("TERM")


  def * : ProvenShape[UserSubjects] = (userSubjectId, name, description, term) <> ((UserSubjects.apply _).tupled, UserSubjects.unapply)
}

object UserSubjectsTable extends TableQuery(new UserSubjectsTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userSubjectId: String): Future[Option[UserSubjects]] = {
    db.run(this.filter(_.userSubjectId === userSubjectId).result).map(_.headOption)
  }

  def saveEntity(userSubjects: UserSubjects): Future[UserSubjects] = {
    db.run(this returning this.map(_.userSubjectId) into ((acc, userSubjectId) => acc.copy(userSubjectId = userSubjectId)) += userSubjects)
  }

  def getEntities: Future[Seq[UserSubjects]] = {
    db.run(UserSubjectsTable.result)
  }

  def deleteEntity(userSubjectId: String): Future[Int] = {
    db.run(this.filter(_.userSubjectId === userSubjectId).delete)
  }

  def createTable = {
    db.run(
      UserSubjectsTable.schema.createIfNotExists
    ).isCompleted
  }

}