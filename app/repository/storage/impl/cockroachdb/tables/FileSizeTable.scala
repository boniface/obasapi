package repository.storage.impl.cockroachdb.tables

import domain.storage.FileSize
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

//@Deprecated
//class FileSizeTable(tag: Tag) extends Table[FileSize](tag, "FILE_SIZE") {
//  def size: Rep[Int] = column[Int]("SIZE", O.PrimaryKey)
//
//  def * : ProvenShape[FileSize] = (size) <> ((FileSize.apply _).tupled, FileSize.unapply)
//}
//
//object FileSizeTable extends TableQuery(new FileSizeTable(_)) {
//  def db: driver.api.Database = PgDBConnection.db
//
//  def getEntity(size: String): Future[Option[FileSize]] = {
//    db.run(this.filter(_.size === size).result).map(_.headOption)
//  }
//
//  def saveEntity(mailApi: FileSize): Future[Option[FileSize]] = {
//    db.run(
//      (this returning this).insertOrUpdate(mailApi)
//    )
//  }
//
//  def getEntities: Future[Seq[FileSize]] = {
//    db.run(FileSizeTable.result)
//  }
//
//  def deleteEntity(size: String): Future[Int] = {
//    db.run(this.filter(_.size === size).delete)
//  }
//
//  def createTable: Boolean = {
//    db.run(
//      FileSizeTable.schema.createIfNotExists
//    ).isCompleted
//  }
//
//}