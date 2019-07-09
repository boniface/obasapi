package repository.location.impl.cockroachdb.tables

import domain.location.Location
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class LocationTable(tag: Tag) extends Table[Location](tag, "LOCATION") {
  def locationId: Rep[String] = column[String]("SCHOOL_ID", O.PrimaryKey)

  def name: Rep[String] = column[String]("NAME")

  def latitude: Rep[String] = column[String]("LATITUDE")

  def longitude: Rep[String] = column[String]("LONGITUDE")

  def code: Rep[String] = column[String]("CODE")

  def locationTypeId: Rep[String] = column[String]("LOCATION_TYPE_ID")

  def parentId:Rep[Option[String]] = column[Option[String]]("PARENT_ID")

  def * : ProvenShape[Location] = (locationId, name, latitude, longitude, code, locationTypeId, parentId) <> ((Location.apply _).tupled, Location.unapply)
}

object LocationTable extends TableQuery(new LocationTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(locationId: String): Future[Option[Location]] = {
    db.run(this.filter(_.locationId === locationId).result).map(_.headOption)
  }

  def saveEntity(location: Location): Future[Location] = {
    db.run(this returning this.map(_.locationId) into ((acc, locationId) => acc.copy(locationId = locationId)) += location)
  }

  def getEntities: Future[Seq[Location]] = {
    db.run(LocationTable.result)
  }

  def deleteEntity(locationId: String): Future[Int] = {
    db.run(this.filter(_.locationId === locationId).delete)
  }

  def createTable = {
    db.run(
      LocationTable.schema.createIfNotExists
    ).isCompleted
  }

}