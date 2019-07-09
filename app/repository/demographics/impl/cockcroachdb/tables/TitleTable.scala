package repository.demographics.impl.cockcroachdb.tables

import  domain.demographics.Title
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TitleTable(tag: Tag) extends Table[Title](tag, _tableName = "Title"){

  def titleId: Rep[String] = column[String]("TITLE_ID", O.PrimaryKey)

  def titlename: Rep[String] = column[String]("TITLE_NAME")

 

  def * : ProvenShape[Title] = (titleId, titlename) <> ((Title.apply _).tupled, Title.unapply)
}

object TitleTable extends TableQuery(new TitleTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(titleId: String): Future[Option[Title]] = {
    db.run(this.filter(_.titleId === titleId).result).map(_.headOption)
  }

  def saveEntity(title: Title): Future[Title] = {
    db.run(this returning this.map(_.titleId) into ((acc, titleId) => acc.copy(titleId = titleId)) += title)
  }

  def getEntities: Future[Seq[Title]] = {
    db.run(TitleTable.result)
  }

  def deleteEntity(titleId: String): Future[Int] = {
    db.run(this.filter(_.titleId === titleId).delete)
  }

  def createTable = {
    db.run(
      TitleTable.schema.createIfNotExists
    ).isCompleted
  }
  
}
