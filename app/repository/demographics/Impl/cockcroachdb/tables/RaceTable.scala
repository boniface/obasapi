package repository.demographics.Impl.cockcroachdb.tables

import domain.demographics.Race
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class RaceTable(tag: Tag) extends Table[Race](tag, _tableName = "RACE"){

  def raceId: Rep[String] = column[String]("RACE_ID", O.PrimaryKey)

  def raceName: Rep[String] = column[String]("RACE_NAME")



  def * : ProvenShape[Race] = (raceId, raceName) <> ((Race.apply _).tupled, Race.unapply)
}

object RaceTable extends TableQuery(new RaceTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(raceId: String): Future[Option[Race]] = {
    db.run(this.filter(_.raceId === raceId).result).map(_.headOption)
  }

  def saveEntity(race: Race): Future[Race] = {
    db.run(this returning this.map(_.raceId) into ((acc, raceId) => acc.copy(raceId = raceId)) += race)
  }

  def getEntities: Future[Seq[Race]] = {
    db.run(RaceTable.result)
  }

  def deleteEntity(raceId: String): Future[Int] = {
    db.run(this.filter(_.raceId === raceId).delete)
  }

  def createTable = {
    db.run(
      RaceTable.schema.createIfNotExists
    ).isCompleted
  }
  
}
