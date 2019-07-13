package repository.documents.impl.cockcroachdb.tables

import domain.documents.DocumentType
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class  DocumentTypeTable(tag: Tag) extends Table[DocumentType](tag, _tableName = "DOCUMENT_TYPE"){
  def documentTypeId: Rep[String] = column[String]("MAIL_ID", O.PrimaryKey)

  def documentTypename: Rep[String] = column[String]("MAIL_KEY")


  def * : ProvenShape[DocumentType] = (documentTypeId, documentTypename) <> ((DocumentType.apply _).tupled, DocumentType.unapply)
}

object DocumentTypeTable extends TableQuery(new DocumentTypeTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(documentTypeId: String): Future[Option[DocumentType]] = {
    db.run(this.filter(_.documentTypeId === documentTypeId).result).map(_.headOption)
  }

  def saveEntity(document: DocumentType): Future[DocumentType] = {
    db.run(this returning this.map(_.documentTypeId) into ((acc, documentTypeId) => acc.copy(documentTypeId = documentTypeId)) += document)
  }

  def getEntities: Future[Seq[DocumentType]] = {
    db.run(DocumentTypeTable.result)
  }

  def deleteEntity(documentTypeId: String): Future[Int] = {
    db.run(this.filter(_.documentTypeId === documentTypeId).delete)
  }

  def createTable = {
    db.run(
      DocumentTypeTable.schema.createIfNotExists
    ).isCompleted
  }

}