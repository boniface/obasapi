package repository.users.Impl.cockroachdb.tables

import domain.users.UserDocuments
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UserDocumentsTable(tag: Tag) extends Table[UserDocuments](tag, "USERDOCUMENTS") {
  def userDocumentsId: Rep[String] = column[String]("USER_DOCUMENTS_ID", O.PrimaryKey)

  def documentId: Rep[String] = column[String]("DOCUMENT_ID")

  def * : ProvenShape[UserDocuments] = (userDocumentsId, documentId) <> ((UserDocuments.apply _).tupled, UserDocuments.unapply)
}

object UserDocumentsTable extends TableQuery(new UserDocumentsTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(userDocumentsId: String): Future[Option[UserDocuments]] = {
    db.run(this.filter(_.userDocumentsId === userDocumentsId).result).map(_.headOption)
  }

  def saveEntity(userDocuments: UserDocuments): Future[UserDocuments] = {
    db.run(this returning this.map(_.userDocumentsId) into ((acc, userDocumentsId) => acc.copy(userDocumentsId = userDocumentsId)) += userDocuments)
  }

  def getEntities: Future[Seq[UserDocuments]] = {
    db.run(UserDocumentsTable.result)
  }

  def deleteEntity(userDocumentsId: String): Future[Int] = {
    db.run(this.filter(_.userDocumentsId === userDocumentsId).delete)
  }

  def createTable = {
    db.run(
      UserDocumentsTable.schema.createIfNotExists
    ).isCompleted
  }

}