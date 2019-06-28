package repository.documents.Impl.cockcroachdb.tables

//import akka.http.scaladsl.model.DateTime
import akka.http.javadsl.model.DateTime
import domain.documents.Document
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class DocumentTable(tag:Tag) extends Table[Document](tag, _tableName = "DOCUMENT"){

  def email: Rep[String] = column[String]("EMAIL", O.PrimaryKey)

  def documentsId: Rep[String] = column[String]("DOCUMENTS_ID")

  def documentTypeId: Rep[String] = column[String]("DOCUMENTTYPE_ID")

  def description: Rep[String] = column[String]("DESCRIPTION")

  def url: Rep[String] = column[String]("URL")

  def mime: Rep[String] = column[String]("MIME")

  def date: Rep[Long] = column[Long]("DATE")

  def permission: Rep[String] = column[String]("PERMISSION")

  def * : ProvenShape[Document] = (email, documentsId, documentTypeId,description,url,mime,date,permission) <> ((Document.apply _).tupled)
  
}

object DocumentTable extends TableQuery(new DocumentTable(_)){
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(email: String): Future[Option[Document]] = {
    db.run(this.filter(_.email === email).result).map(_.headOption)
  }

  def saveEntity(document: Document): Future[Document] = {
    db.run(this returning this.map(_.email) into ((acc, email) => acc.copy(email = email)) += document)
  }

  def getEntities: Future[Seq[Document]] = {
    db.run(DocumentTable.result)
  }

  def deleteEntity(email: String): Future[Int] = {
    db.run(this.filter(_.email === email).delete)
  }

  def createTable = {
    db.run(
      DocumentTable.schema.createIfNotExists
    ).isCompleted
  }

}