package repository.users.impl.cockroachdb.tables

import domain.users.UserDocument
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
class UserDocumentTableCreate(tag: Tag) extends Table[UserDocument](tag, "user_document") {
  def userId: Rep[String] = column[String]("user_id")

  def documentId: Rep[String] = column[String]("document_id")

  override def * : ProvenShape[UserDocument] = (userId, documentId) <> ((UserDocument.apply _).tupled, UserDocument.unapply)

  def pk = primaryKey("pk_user_document", (userId, documentId))
}

object UserDocumentTableCreate extends TableQuery(new UserDocumentTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable: Boolean = db.run(
    UserDocumentTableCreate.schema.createIfNotExists
  ).isCompleted

}

/**
 * Used for DML
 * @param tag
 */
class UserDocumentTable(tag: Tag) extends Table[UserDocument](tag, "user_document") {
  def userId: Rep[String] = column[String]("user_id", O.PrimaryKey)

  def documentId: Rep[String] = column[String]("document_id", O.PrimaryKey)

  def * : ProvenShape[UserDocument] = (userId, documentId) <> ((UserDocument.apply _).tupled, UserDocument.unapply)
}

object UserDocumentTable extends TableQuery(new UserDocumentTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userId: String, documentId: String): Future[Option[UserDocument]] = {
    db.run(this.filter(_.userId === userId).filter(_.documentId === documentId).result).map(_.headOption)
  }

  def saveEntity(userDocuments: UserDocument): Future[Option[UserDocument]] = {
    db.run(
      (this returning this).insertOrUpdate(userDocuments)
    )
  }

  def getEntities: Future[Seq[UserDocument]] = {
    db.run(UserDocumentTable.result)
  }

  def deleteEntity(userId: String, documentId: String): Future[Int] = {
    db.run(this.filter(_.userId === userId).filter(_.documentId === documentId).delete)
  }

  def getUserDocuments(userId: String): Future[Seq[UserDocument]] = {
    db.run(this.filter(_.userId === userId).result)
  }

}