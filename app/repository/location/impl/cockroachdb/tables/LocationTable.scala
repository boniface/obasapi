package repository.location.impl.cockroachdb.tables

import domain.location.Location
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class LocationTable(tag: Tag) extends Table[Location](tag, "location") {
  def locationId: Rep[String] = column[String]("location_id", O.PrimaryKey)

  def name: Rep[String] = column[String]("name")

  def latitude: Rep[String] = column[String]("latitude")

  def longitude: Rep[String] = column[String]("longitude")

  def code: Rep[String] = column[String]("code")

  def locationTypeId: Rep[String] = column[String]("location_type_id")

  def parentId:Rep[Option[String]] = column[Option[String]]("parent_id")

  def * : ProvenShape[Location] = (locationId, name, latitude, longitude, code, locationTypeId, parentId) <> ((Location.apply _).tupled, Location.unapply)
}

object LocationTable extends TableQuery(new LocationTable(_)) {
  def db: driver.api.Database = PgDBConnection.db

  def getEntity(locationId: String): Future[Option[Location]] = {
    db.run(this.filter(_.locationId === locationId).result).map(_.headOption)
  }

  def saveEntity(location: Location): Future[Option[Location]] = {
    db.run(
      (this returning this).insertOrUpdate(location)
    )
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