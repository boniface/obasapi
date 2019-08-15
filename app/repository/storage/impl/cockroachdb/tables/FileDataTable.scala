package repository.storage.impl.cockroachdb.tables

import domain.storage.FileData
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class FileDataTable(tag: Tag) extends Table[FileData](tag, "FILE_DATA_TABLE") {
  def id: Rep[String] = column[String]("FILE_ID", O.PrimaryKey)

  def url: Rep[String] = column[String]("URL")

  def etag: Rep[String] = column[String]("ETAG")

  def * : ProvenShape[FileData] = (id, url, etag) <> ((FileData.apply _).tupled, FileData.unapply)
}

object FileDataTable extends TableQuery(new FileDataTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(id: String): Future[Option[FileData]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def saveEntity(mailApi: FileData): Future[Option[FileData]] = {
    db.run(
      (this returning this).insertOrUpdate(mailApi)
    )
  }

  def getEntities: Future[Seq[FileData]] = {
    db.run(FileDataTable.result)
  }

  def deleteEntity(id: String): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  def createTable: Boolean = {
    db.run(
      FileDataTable.schema.createIfNotExists
    ).isCompleted
  }

}
