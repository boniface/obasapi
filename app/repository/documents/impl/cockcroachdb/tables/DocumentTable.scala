package repository.documents.impl.cockcroachdb.tables

import java.time.LocalDateTime

import domain.documents.Document
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DocumentTable(tag:Tag) extends Table[Document](tag, _tableName = "document"){

  def documentId: Rep[String] = column[String]("document_id", O.PrimaryKey)

  def documentTypeId: Rep[String] = column[String]("document_type_id")

  def description: Rep[String] = column[String]("description")

  def url: Rep[String] = column[String]("url")

  def mime: Rep[String] = column[String]("mime")

  def date: Rep[LocalDateTime] = column[LocalDateTime]("date")

  def permission: Rep[String] = column[String]("permission")

  def documentStatus: Rep[String] = column[String]("document_status")

  def * : ProvenShape[Document] = (documentId, documentTypeId,description,url,mime,date,permission, documentStatus) <> ((Document.apply _).tupled, Document.unapply)
  
}

object DocumentTable extends TableQuery(new DocumentTable(_)){
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(documentId: String): Future[Option[Document]] = {
    db.run(this.filter(_.documentId === documentId).result).map(_.headOption)
  }

  def saveEntity(document: Document): Future[Option[Document]] = {
    db.run(
      (this returning this).insertOrUpdate(document)
    )
  }

  def getEntities: Future[Seq[Document]] = {
    db.run(DocumentTable.result)
  }

  def deleteEntity(documentId: String): Future[Int] = {
    db.run(this.filter(_.documentId === documentId).delete)
  }

  def createTable = {
    db.run(
      DocumentTable.schema.createIfNotExists
    ).isCompleted
  }

}