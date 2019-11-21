package repository.institutions.impl.cockroachdb.tables

import domain.institutions.InstitutionLocation
import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape
import util.connections.PgDBConnection
import util.connections.PgDBConnection.driver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class InstitutionLocationTable(tag: Tag) extends Table[InstitutionLocation](tag, "institution_location") {

  def institutionId: Rep[String] = column[String]("institution_id", O.PrimaryKey)

  def locationId: Rep[String] = column[String]("location_id")

  def longitude: Rep[String] = column[String]("longitude")

  def latitude: Rep[String] = column[String]("latitude")

  override def * : ProvenShape[InstitutionLocation] = (institutionId, locationId, longitude, latitude) <> ((InstitutionLocation.apply _).tupled, InstitutionLocation.unapply)
}

object InstitutionLocationTable extends TableQuery(new InstitutionLocationTable(_)) {

  def db: driver.api.Database = PgDBConnection.db

  def saveEntity(institutionLocation: InstitutionLocation): Future[Option[InstitutionLocation]] =
    db.run((this returning this).insertOrUpdate(institutionLocation))

  def getEntity(institutionId: String): Future[Option[InstitutionLocation]] =
    db.run(this.filter(_.institutionId === institutionId).result).map(_.headOption)

  def deleteEntity(institutionId: String): Future[Int] =
    db.run(this.filter(_.institutionId === institutionId).delete)

  def getEntities: Future[Seq[InstitutionLocation]] =
    db.run(InstitutionLocationTable.result)

  def createTable: Boolean = {
    db.run(
      InstitutionLocationTable.schema.createIfNotExists
    ).isCompleted
  }

}
