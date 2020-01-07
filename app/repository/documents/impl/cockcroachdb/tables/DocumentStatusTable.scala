package repository.documents.impl.cockcroachdb.tables

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver
import java.time.LocalDateTime

import domain.documents.DocumentStatus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Used for DDL (to create table with composite key)
 *
 * @param tag
 */
class DocumentStatusTableCreate(tag: Tag) extends Table[DocumentStatus](tag, "document_status") {

  def documentId: Rep[String] = column[String]("document_id")

  def statusId: Rep[String] = column[String]("status_id")

  def modifiedBy: Rep[String] = column[String]("modified_by")

  def comment: Rep[Option[String]] = column[Option[String]]("comment")

  def dateTime: Rep[LocalDateTime] = column[LocalDateTime]("date_time")

  def * : ProvenShape[DocumentStatus] = (documentId, statusId, modifiedBy, comment, dateTime) <> ((DocumentStatus.apply _).tupled, DocumentStatus.unapply)

  def pk = primaryKey("pk_document_status", (documentId, statusId, dateTime))
}

object DocumentStatusTableCreate extends TableQuery(new DocumentStatusTableCreate(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def createTable = {
    db.run(
      DocumentStatusTableCreate.schema.createIfNotExists
    ).isCompleted
  }
}

/**
 * Used for DML
 *
 * @param tag
 */
class DocumentStatusTable(tag: Tag) extends Table[DocumentStatus](tag, "document_status") {

  def documentId: Rep[String] = column[String]("document_id", O.PrimaryKey)

  def statusId: Rep[String] = column[String]("status_id", O.PrimaryKey)

  def modifiedBy: Rep[String] = column[String]("modified_by")

  def comment: Rep[Option[String]] = column[Option[String]]("comment")

  def dateTime: Rep[LocalDateTime] = column[LocalDateTime]("date_time", O.PrimaryKey)

  def * : ProvenShape[DocumentStatus] = (documentId, statusId, modifiedBy, comment, dateTime) <> ((DocumentStatus.apply _).tupled, DocumentStatus.unapply)
}

object DocumentStatusTable extends TableQuery(new DocumentStatusTable(_)) {
  type DomainObject = DocumentStatus

  def db: driver.api.Database = PgDBConnection.db

  def getEntitiesForDocumentnStatus(documentId: String, statusId: String): Future[Seq[DomainObject]] = {
    db.run(this.filter(_.documentId === documentId).filter(_.statusId === statusId).result)
  }

  def getLatestForDocumentnStatus(documentId: String, statusId: String): Future[Option[DomainObject]] = {
    db.run(this.filter(_.documentId === documentId).filter(_.statusId === statusId).result)
      .map(_.sorted(DocumentStatus.orderByDateTime)).map(_.headOption)
  }

  def getEntitiesForDocument(documentId: String): Future[Seq[DomainObject]] = {
    db.run(this.filter(_.documentId === documentId).result)
  }

  def getLatestForDocument(documentId: String): Future[Option[DomainObject]] = {
    db.run(this.filter(_.documentId === documentId).result)
      .map(_.sorted(DocumentStatus.orderByDateTime)).map(_.headOption)
  }

  def saveEntity(documentStatus: DocumentStatus): Future[Option[DomainObject]] = {
    db.run(
      (this returning this).insertOrUpdate(documentStatus)
    )
  }

  def getEntities: Future[Seq[DomainObject]] = {
    db.run(DocumentStatusTable.result)
  }
}
