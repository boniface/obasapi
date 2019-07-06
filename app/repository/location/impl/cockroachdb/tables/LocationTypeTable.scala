package repository.location.impl.cockroachdb.tables

import domain.location.LocationType
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class LocationTypeTable(tag: Tag) extends Table[LocationType](tag, "LOCATION_TYPE") {
  def locationTypeId: Rep[String] = column[String]("LOCATION_TYPE_ID", O.PrimaryKey)

  def name: Rep[String] = column[String]("NAME")

  def * : ProvenShape[LocationType] = (locationTypeId, name) <> ((LocationType.apply _).tupled, LocationType.unapply)
}

object LocationTypeTable extends TableQuery(new LocationTypeTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(locationTypeId: String): Future[Option[LocationType]] = {
    db.run(this.filter(_.locationTypeId === locationTypeId).result).map(_.headOption)
  }

  def saveEntity(locationType: LocationType): Future[LocationType] = {
    db.run(this returning this.map(_.locationTypeId) into ((acc, locationTypeId) => acc.copy(locationTypeId = locationTypeId)) += locationType)
  }

  def getEntities: Future[Seq[LocationType]] = {
    db.run(LocationTypeTable.result)
  }

  def deleteEntity(locationTypeId: String): Future[Int] = {
    db.run(this.filter(_.locationTypeId === locationTypeId).delete)
  }

  def createTable = {
    db.run(
      LocationTypeTable.schema.createIfNotExists
    ).isCompleted
  }

}
