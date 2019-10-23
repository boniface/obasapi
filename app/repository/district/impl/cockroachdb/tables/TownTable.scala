package repository.district.impl.cockroachdb.tables

import domain.district.Town
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class TownTable(tag: Tag) extends Table[Town] (tag, _tableName = "town") {

  def townCode: Rep[String] = column[String]("town_code", O.PrimaryKey)

  def townName: Rep[String] = column[String]("town_name")

  override def * : ProvenShape[Town] = (townCode,townName) <> ((Town.apply _).tupled, Town.unapply)
}

object TownTable extends TableQuery(new TownTable(_)){
  def db: driver.api.Database =PgDBConnection.db

  def getEntity(townCode:String):Future[Option[Town]] ={
    db.run(this.filter(_.townCode === townCode).result).map(_.headOption)
  }

  def saveEntity(town: Town): Future[Option[Town]] = {
    db.run(
      (this returning this).insertOrUpdate(town)
    )
  }

  def getEntities: Future[Seq[Town]] = {
    db.run(TownTable.result)
  }

  def deleteEntity(townCode: String): Future[Int] = {
    db.run(this.filter(_.townCode === townCode).delete)
  }

  def createTable = {
    db.run(
      TownTable.schema.createIfNotExists
    ).isCompleted
  }

}